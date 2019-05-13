package com.svcg.StockCustom.service;


import com.svcg.StockCustom.entity.User;
import com.svcg.StockCustom.enums.RolName;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {

    User saveUser(User user);

    Page<User> getUsers(Pageable pageable);

    List<RolName> getRolesUsers();

    User getUserByUsername(String username);

    User setDisabledByUsername(String username);

    User getUserById(Long id);

    User updateUser(User user);

    String obtenerToken(HttpServletRequest request);

    JSONObject obtenerJsonDeToken(String token);
}
