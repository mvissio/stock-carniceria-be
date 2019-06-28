
package com.svcg.StockCustom.controller;

import com.svcg.StockCustom.constant.Constant;
import com.svcg.StockCustom.entity.User;
import com.svcg.StockCustom.enums.RolName;
import com.svcg.StockCustom.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {


    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;


    private static final Logger logger = LoggerFactory
            .getLogger(UserController.class);

    @GetMapping("")
    public Page<User> getUsers(Pageable pageable) {
        return userService.getUsers(pageable);
    }

    @PostMapping("")
    public User addUser(@Valid @RequestBody User user, BindingResult bindingResult) throws MethodArgumentNotValidException {
    	if (bindingResult.hasErrors()) {
        	bindingResult.getFieldErrors().stream().forEach(f -> logger.error(String.format(Constant.CONCAT2S, f.getField(), f.getDefaultMessage())));        	
            throw new MethodArgumentNotValidException(MethodParameter.forExecutable(User.class.getDeclaredConstructors()[1],0), bindingResult);
        }
        return userService.saveUser(user);

    }

    @PutMapping("")
    public User updateUser(@Valid @RequestBody User user, BindingResult bindingResult) throws MethodArgumentNotValidException {
    	if (bindingResult.hasErrors()) {
        	bindingResult.getFieldErrors().stream().forEach(f -> logger.error(String.format(Constant.CONCAT2S, f.getField(), f.getDefaultMessage())));        	
            throw new MethodArgumentNotValidException(MethodParameter.forExecutable(User.class.getDeclaredConstructors()[1],0), bindingResult);
        }
        return userService.updateUser(user);

    }

    @DeleteMapping("/username")
    public User deleteUser(String username) {
        return userService.setDisabledByUsername(username);
    }


    @GetMapping("roles")
    public List<RolName> getRolesUsers() {
        return userService.getRolesUsers();
    }

    @GetMapping("/username")
    public User getUserByUsername(String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping("id")
    public User getUserById(Long id) {
        return userService.getUserById(id);
    }
}
