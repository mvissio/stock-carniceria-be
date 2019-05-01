package com.svcg.StockCustom.constant;

public class Constant {

    // Spring security

    public static final String LOGIN_URL = "/login";
    public static final String RECOVER_PASSWORD = "/recoverPassword";
    public static final String HEADER_AUTHORIZATION_KEY = "Authorization";
    public static final String TOKEN_BEARER_PREFIX = "Bearer ";

    // JWT

    public static final String ISSUER_INFO = "https://www.autentia.com/";
    public static final String SUPER_SECRET_KEY = "1234";
    public static final long TOKEN_EXPIRATION_TIME = 864_000_000; // 10 day
    public static final String USER_ROLE = "rol";
    public static final String USER_ID= "userId";


    //Password
    public static final String NUMEROS = "0123456789";
    public static final String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
    public static final String ESPECIALES = "ñÑ";

}
