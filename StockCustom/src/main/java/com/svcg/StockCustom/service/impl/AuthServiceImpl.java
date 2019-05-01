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
    public String resetPasswordByEmail(String email) throws NullPointerException {
        User user = userRepository.findByEmail(email);
        String responseMessage;
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get("MESSAGE_MAIL_NOT_EXISTS"), null);
        }
        String pass = newPass();
        BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
        user.setPassword(pe.encode(pass));
        sendNewPass(pass, user.getEmail());

        try {
            userRepository.save(user);
            responseMessage = this.messages.get("MESSAGE_MAIL_RESTORE");
        } catch (Exception e) {
            logger.error("Exception: {} ", e);
            throw new ResponseStatusException(HttpStatus.CONFLICT, this.messages.get("MESSAGE_CANT_RESET_PASSWORD"), null);
        }
        return responseMessage;
    }

    private String newPass() {
        return getPassword(
                Constant.MINUSCULAS+
                        Constant.MAYUSCULAS+
                        Constant.ESPECIALES,10);
    }

    private void sendNewPass(String pass, String email) throws NullPointerException {

        try {
            mailAdapterImpl.sendMailHTML(email, "Password Restablecido", "Nuevo Password : " + pass);
        }catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, this.messages.get("MESSAGE_CANT_SEND_MAIL_NEW_PASSWORD"), null);
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
