package com.svcg.StockCustom.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.svcg.StockCustom.service.AuthService;

@RestController
public class AuthController {

  @Autowired
  @Qualifier("authServiceImpl")
  private AuthService authService;

    @PostMapping("/recoverPassword")
    public ResponseEntity<Void> recoverPassword(@RequestParam String email) throws IOException {
		this.authService.resetPasswordByEmail(email);
		return ResponseEntity.noContent().build();
    }

}
