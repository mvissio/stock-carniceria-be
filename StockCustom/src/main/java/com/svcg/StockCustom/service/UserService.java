package com.svcg.StockCustom.service;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.svcg.StockCustom.enums.RolName;
import com.svcg.StockCustom.service.dto.UserDTO;

public interface UserService {

	UserDTO saveUser(@Valid UserDTO userDTO);

    Page<UserDTO> getUsers(Pageable pageable);

    List<RolName> getRolesUsers();
    
    UserDTO getUserByUsername(String username);
    
    Page<UserDTO> getUserByUsername(String username, Pageable pageable);

    UserDTO setDisabledByUsername(String username);

    UserDTO getUserById(Long id);

    UserDTO updateUser(@Valid UserDTO userDTO);

    String obtenerToken(HttpServletRequest request);

    JSONObject obtenerJsonDeToken(String token);
}
