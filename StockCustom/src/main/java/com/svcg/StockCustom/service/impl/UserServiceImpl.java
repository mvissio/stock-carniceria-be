package com.svcg.StockCustom.service.impl;

import com.svcg.StockCustom.component.Messages;
import com.svcg.StockCustom.constant.Constant;
import com.svcg.StockCustom.entity.Rol;
import com.svcg.StockCustom.enums.RolName;
import com.svcg.StockCustom.exceptions.CustomRuntimeException;
import com.svcg.StockCustom.repository.RolRepository;
import com.svcg.StockCustom.repository.UserRepository;
import com.svcg.StockCustom.service.UserService;
import com.svcg.StockCustom.service.converter.RolConverter;
import com.svcg.StockCustom.service.converter.UserConverter;
import com.svcg.StockCustom.service.dto.UserDTO;

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
    
    @Autowired
    private UserConverter userConverter;

    @Autowired
    private RolConverter rolConverter;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<com.svcg.StockCustom.entity.User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
        	throw new ResponseStatusException(HttpStatus.NOT_FOUND,  this.messages.get(Constant.MESSAGE_USER_NOT_FOUND));
        }
        List<GrantedAuthority> authorities = buildAuthorities(user.get());
        return buildUser(user.get(), authorities);
    }


    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        if (userDTO == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.messages.get(Constant.MESSAGE_CANT_CREATE_USER));
        }
        if (userNameExist(userDTO.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(Constant.CONCAT2S, this.messages.get(Constant.MESSAGE_USER_EXIST), userDTO.getUsername()));
        }
        if (emailExist(userDTO.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(Constant.CONCAT2S, this.messages.get(Constant.MESSAGE_EMAIL_EXIST), userDTO.getEmail()));
        }

        if (userDTO.getPassword() != null) {
        	userDTO.setPassword(userDTO.getPassword());
        }

        return saveUserAndRol(userDTO, true);
    }

    /**
     * Guardo el usuario con sus roles
     */

    private UserDTO saveUserAndRol(UserDTO userDTO, Boolean isSave) {
    	UserDTO newUserDTO;

        try {
        	Optional<Rol> rol = rolRepository.findRolByName(userDTO.getRol().getName());
        	if (rol.isPresent()) {
        		userDTO.setRol(this.rolConverter.toDTO(rol.get()));        		
        	}
        	newUserDTO = this.userConverter.toDTO(userRepository.save(this.userConverter.toEntity(userDTO)));
            /**
             * Devuelvo el user creado con el rol seteado
             */
        } catch (Exception e) {
            logger.error(Constant.EXCEPTION, e);
            String message = (isSave) ? this.messages.get(Constant.MESSAGE_CANT_CREATE_USER) : this.messages.get(Constant.MESSAGE_CANT_UPDATE_USER);
            throw new ResponseStatusException(HttpStatus.CONFLICT, message);
        }
        return newUserDTO;
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        if (userDTO == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.messages.get(Constant.MESSAGE_CANT_UPDATE_USER));
        }
        Optional<com.svcg.StockCustom.entity.User> previousUser = userRepository.findByUsername(userDTO.getUsername());
        if (!previousUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_USER_NOT_FOUND));
        }
        if (!previousUser.get().getEmail().equals(userDTO.getEmail()) && emailExist(userDTO.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(Constant.CONCAT2S, this.messages.get(Constant.MESSAGE_EMAIL_EXIST), userDTO.getEmail()));
        }
        return saveUserAndRol(userDTO, false);
    }

    @Override
    public Page<UserDTO> getUsers(Pageable pageable) {
        Page<com.svcg.StockCustom.entity.User> users = userRepository.findAll(pageable);
        if (users.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,  this.messages.get(Constant.MESSAGE_USER_NOT_FOUND));
        }
        return users.map(this.userConverter::toDTO);
    }

    private User buildUser(com.svcg.StockCustom.entity.User user, List<GrantedAuthority> authorities) {
        return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildAuthorities(com.svcg.StockCustom.entity.User user) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRol().getName()));
        authorities.add(new SimpleGrantedAuthority(user.getUserId().toString()));
        return new ArrayList<>(authorities);
    }

    private Boolean emailExist(String email) {
        Optional<com.svcg.StockCustom.entity.User> user = userRepository.findByEmail(email);
        return user.isPresent();
    }

    private Boolean userNameExist(String username) {
        Optional<com.svcg.StockCustom.entity.User> user = userRepository.findByUsername(username);
        return user.isPresent();
    }


    @Override
    public List<RolName> getRolesUsers() {
    	return Arrays.asList(RolName.values());
    }

    @Override
    public Page<UserDTO> getUserByUsername(String username, Pageable pageable) {
    	Optional<Page<com.svcg.StockCustom.entity.User>> users = userRepository.findByUsernameContaining(username, pageable);
        if(!users.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_USER_NOT_FOUND));
        }

        return users.get().map(this.userConverter::toDTO);
    }
    
    @Override
    public UserDTO getUserByUsername(String username) {
    	Optional<com.svcg.StockCustom.entity.User> user = userRepository.findByUsername(username);
        if(!user.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_USER_NOT_FOUND));
        }

        return this.userConverter.toDTO(user.get());
    }

    @Override
    public UserDTO getUserById(Long id) {
        Optional<com.svcg.StockCustom.entity.User> user = userRepository.findByUserId(id);
        if(!user.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, this.messages.get(Constant.MESSAGE_USER_NOT_FOUND));
        }

        return this.userConverter.toDTO(user.get());
    }

    public String obtenerToken(HttpServletRequest request) {
        try {
            String authorization = request.getHeader("authorization");
            authorization = authorization.replace("Barer", "");
            authorization = authorization.trim();
            org.apache.tomcat.util.codec.binary.Base64 decoder = new Base64(true);
            return new String(decoder.decode(authorization.getBytes()));
        } catch (Exception e) {
            throw new CustomRuntimeException(e);
        }
    }

    public JSONObject obtenerJsonDeToken(String token) {
        try {
            token = token.replace("}{", "}, {");
            String[] split = token.split(", ");
            return new JSONObject(split[1]);
        } catch (Exception e) {
            throw new CustomRuntimeException(e);
        }
    }

    @Override
    public UserDTO setDisabledByUsername(String username) {
    	UserDTO userDTO = getUserByUsername(username);
    	userDTO.setEnabled(false);
        userDTO = saveUserAndRol(userDTO, false);
        return userDTO;
    }
}
