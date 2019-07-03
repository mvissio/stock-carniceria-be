package com.svcg.StockCustom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import com.svcg.StockCustom.model.ResponseModel;
import com.svcg.StockCustom.service.AuthService;

@RestController
public class AuthController {

  @Autowired
  @Qualifier("authServiceImpl")
  private AuthService authService;

  @PostMapping("/recoverPassword")
  public ResponseModel<String> recoverPassword(@RequestParam String email) throws IOException {
    	ResponseModel<String> responseModel = new ResponseModel<>(HttpStatus.OK.value(), HttpStatus.OK.toString());
    	responseModel.setResult(authService.resetPasswordByEmail(email));
		return responseModel;
    }

}
