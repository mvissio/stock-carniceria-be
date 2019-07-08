
package com.svcg.StockCustom.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.svcg.StockCustom.constant.Constant;
import com.svcg.StockCustom.enums.RolName;
import com.svcg.StockCustom.service.UserService;
import com.svcg.StockCustom.service.dto.UserDTO;

@RestController
@RequestMapping(value = "/users")
public class UserController {


    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;


    private static final Logger logger = LoggerFactory
            .getLogger(UserController.class);

    @GetMapping("")
    public ResponseEntity<Page<UserDTO>> getUsers(Pageable pageable) {
    	return ResponseEntity.ok(this.userService.getUsers(pageable));
    }

    @PostMapping("")
    public ResponseEntity<UserDTO> addUser(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        if (bindingResult.hasErrors()) {
        	bindingResult.getFieldErrors().stream().forEach(f -> logger.error(String.format(Constant.CONCAT2S, f.getField(), f.getDefaultMessage())));        	
            throw new MethodArgumentNotValidException(MethodParameter.forExecutable(UserDTO.class.getDeclaredConstructors()[1],0), bindingResult);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userDTO));
    }

    @PutMapping("")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) throws MethodArgumentNotValidException {
        if (bindingResult.hasErrors()) {
        	bindingResult.getFieldErrors().stream().forEach(f -> logger.error(String.format(Constant.CONCAT2S, f.getField(), f.getDefaultMessage())));        	
            throw new MethodArgumentNotValidException(MethodParameter.forExecutable(UserDTO.class.getDeclaredConstructors()[1],0), bindingResult);
        }
        return ResponseEntity.ok(this.userService.updateUser(userDTO));
    }

    @DeleteMapping("/username")
    public ResponseEntity<Void> deleteUser(String username) {
        this.userService.setDisabledByUsername(username);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("roles")
    public List<RolName> getRolesUsers() {
        return this.userService.getRolesUsers();
    }

    @GetMapping("/username")
    public ResponseEntity<UserDTO> getUserByUsername(String username) {
    	UserDTO userDTO = this.userService.getUserByUsername(username);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("id")
    public ResponseEntity<UserDTO> getUserById(Long id) {
    	UserDTO userDTO = this.userService.getUserById(id);
        return ResponseEntity.ok(userDTO);
    }
}
