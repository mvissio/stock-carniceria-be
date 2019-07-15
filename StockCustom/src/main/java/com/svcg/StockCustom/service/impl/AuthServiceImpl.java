package com.svcg.StockCustom.service.impl;

import com.svcg.StockCustom.component.Impl.MailAdapterImpl;
import com.svcg.StockCustom.component.Messages;
import com.svcg.StockCustom.constant.Constant;
import com.svcg.StockCustom.entity.User;
import com.svcg.StockCustom.repository.UserRepository;
import com.svcg.StockCustom.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Optional;

@Service("authServiceImpl")
public class AuthServiceImpl implements AuthService {

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    @Autowired
    @Qualifier("MailAdapterImpl")
    private MailAdapterImpl mailAdapterImpl;

    @Autowired
    Messages messages;

    private static final Logger logger = LoggerFactory
            .getLogger(AuthServiceImpl.class);

    @Override
    public void resetPasswordByEmail(String email) throws IOException {
        Optional<User> user = userRepository.findByEmail(email);
        if (!user.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_EMAIL_NOT_EXISTS));
        }
        String pass = newPass();
        BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
        user.get().setPassword(pe.encode(pass));
        sendNewPass(pass, user.get().getEmail());

        try {
            userRepository.save(user.get());
        } catch (Exception e) {
            logger.error(Constant.EXCEPTION, e);
            throw new ResponseStatusException(HttpStatus.CONFLICT, this.messages.get(Constant.MESSAGE_CANT_RESET_PASS));
        }
    }

    private String newPass() {
        return getPassword(
                Constant.MINUSCULAS+
                        Constant.MAYUSCULAS+
                        Constant.ESPECIALES,10);
    }

    private void sendNewPass(String pass, String email) throws IOException {

        try {
            mailAdapterImpl.sendMailHTML(email, Constant.MESSAGE_MAIL_RESTORE_TITLE, Constant.MESSAGE_MAIL_RESTORE_BODY + pass);
        }catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, this.messages.get(Constant.MESSAGE_CANT_SEND_MAIL_NEW_PASS));
        }
    }

    private String getPassword(String key, int length) {
        String pswd = "";
        for (int i = 0; i < length; i++) {
            pswd+=(key.charAt((int)(Math.random() * key.length())));
        }
        return pswd;
    }
}
