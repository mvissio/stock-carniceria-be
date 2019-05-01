package com.svcg.StockCustom.service;


public interface AuthService {

    String resetPasswordByEmail(String email) throws NullPointerException;
}
