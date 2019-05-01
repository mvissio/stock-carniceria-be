package com.svcg.StockCustom.controller;

import com.svcg.StockCustom.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    @Qualifier("authServiceImpl")
    private AuthService authService;

    @PostMapping("/recoverPassword")
    public String recoverPassword(@RequestParam String email) throws NullPointerException {
        return authService.resetPasswordByEmail(email);
    }

}
