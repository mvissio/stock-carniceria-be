package com.svcg.StockCustom.service.impl;

import com.svcg.StockCustom.component.Messages;
import com.svcg.StockCustom.enums.RolName;
import com.svcg.StockCustom.repository.RolRepository;
import com.svcg.StockCustom.repository.UserRepository;
import com.svcg.StockCustom.service.UserService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service("userServiceImpl")
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    @Autowired
    @Qualifier("rolRepository")
    private RolRepository rolRepository;

    @Autowired
    Messages messages;

    private static final Logger logger = LoggerFactory
            .getLogger(UserServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.svcg.StockCustom.entity.User user = userRepository.findByUsername(username);
        List<GrantedAuthority> authorities = buildAuthorities(user);
        return buildUser(user, authorities);
    }


    @Override
    public com.svcg.StockCustom.entity.User saveUser(com.svcg.StockCustom.entity.User user) {
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.messages.get("MESSAGE_CANT_CREATE_USER"), null);
        }
        if (userNameExist(user.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.messages.get("MESSAGE_USER_EXISTS") + user.getUsername(), null);
        }
        if (emailExist(user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.messages.get("MESSAGE_MAIL_EXISTS") + user.getEmail(), null);
        }

        if (user.getPassword() != null) {
            user.setPassword(user.getPassword());
        }

        /**
         * Guardo el usuario con sus roles
         */
        user = saveUserAndRol(user);

        return user;
    }

    /**
     * Guardo el usuario con sus roles
     */

    private com.svcg.StockCustom.entity.User saveUserAndRol(com.svcg.StockCustom.entity.User user) {
        try {
            user.setRol(rolRepository.findRolByNombre(user.getRol().getNombre()).get());
            user = userRepository.save(user);
            /**
             * Devuelvo el user creado con el rol seteado
             */
        } catch (Exception e) {
            logger.error("Exception: {} ", e);
            throw new ResponseStatusException(HttpStatus.CONFLICT, this.messages.get("MESSAGE_CANT_CREATE_USER"), null);
        }
        return user;
    }

    @Override
    public com.svcg.StockCustom.entity.User updateUser(com.svcg.StockCustom.entity.User user) {
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.messages.get("MESSAGE_CANT_CREATE_USER"), null);
        }
        com.svcg.StockCustom.entity.User previousUser = userRepository.findByUsername(user.getUsername());
        if (previousUser == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get("MESSAGE_NOT_FOUND_USUARIO"), null);
        }
        if (!previousUser.getEmail().equals(user.getEmail()) && emailExist(user.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.messages.get("MESSAGE_MAIL_EXISTS") + user.getEmail());
        }
        user = saveUserAndRol(user);
        return user;
    }

    @Override
    public Page<com.svcg.StockCustom.entity.User> getUsers(Pageable pageable) {
        Page<com.svcg.StockCustom.entity.User> users = userRepository.findAll(pageable);
        if (users.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,  this.messages.get("MESSAGE_NOT_FOUND_USUARIOS"), null);
        }
        return users;
    }

    private User buildUser(com.svcg.StockCustom.entity.User user, List<GrantedAuthority> authorities) {
        return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildAuthorities(com.svcg.StockCustom.entity.User user) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRol().getNombre()));
        authorities.add(new SimpleGrantedAuthority(user.getUserId().toString()));
        return new ArrayList<>(authorities);
    }

    private boolean emailExist(String email) {
        com.svcg.StockCustom.entity.User user = userRepository.findByEmail(email);
        return user != null;
    }

    private boolean userNameExist(String username) {
        com.svcg.StockCustom.entity.User user = userRepository.findByUsername(username);
        return user != null;
    }


    @Override
    public List<RolName> getRolesUsers() {
        List<RolName> rolNames = Arrays.asList(RolName.values());
        return rolNames;
    }

    @Override
    public com.svcg.StockCustom.entity.User getUserByUsername(String username) {
        com.svcg.StockCustom.entity.User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get("MESSAGE_NOT_FOUND_USUARIO"), null);
        }

        return user;
    }

    @Override
    public com.svcg.StockCustom.entity.User getUserById(Long id) {
        com.svcg.StockCustom.entity.User user = userRepository.findByUserId(id);
        if(user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get("MESSAGE_NOT_FOUND_USUARIO"), null);
        }

        return user;
    }

    public String obtenerToken(HttpServletRequest request) {
        try {
            String authorization = request.getHeader("authorization");
            authorization = authorization.replace("Barer", "");
            authorization = authorization.trim();
            org.apache.tomcat.util.codec.binary.Base64 decoder = new Base64(true);
            return new String(decoder.decode(authorization.getBytes()));
        } catch (Exception e) {
            logger.error("Error en obtenerToken" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public JSONObject obtenerJsonDeToken(String token) {
        try {
            token = token.replace("}{", "}, {");
            String[] split = token.split(", ");
            return new JSONObject(split[1]);
        } catch (Exception e) {
            logger.error("Error en obtenerJsonDeToken" + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
