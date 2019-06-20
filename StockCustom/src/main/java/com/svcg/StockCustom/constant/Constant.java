package com.svcg.StockCustom.constant;

public class Constant {
	
	private Constant() {
	    throw new IllegalStateException("Constant class");
	  }

    // Spring security

    public static final String LOGIN_URL = "/login";
    public static final String RECOVER = "/recoverPassword";
    public static final String HEADER_AUTHORIZATION_KEY = "Authorization";
    public static final String TOKEN_BEARER_PREFIX = "Bearer ";
    
 // Logger
 	 public static final String EXCEPTION = "Exception: {} ";
 	 public static final String CONCAT2S = "%s : %s";
	 public static final String CONCAT = "%s";

    // JWT

    public static final String ISSUER_INFO = "";
    public static final String SUPER_SECRET_KEY = "1234";
    public static final long TOKEN_EXPIRATION_TIME = 864_000_000; // 10 day
    public static final String USER_ROLE = "rol";
    public static final String USER_ID= "userId";


    //Password
    public static final String NUMEROS = "0123456789";
    public static final String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
    public static final String ESPECIALES = "ñÑ";
    
    public static final String MESSAGE_USER_EXIST = "message.user.name.userExist";
    public static final String MESSAGE_EMAIL_EXIST = "message.user.email.emailExist";
    public static final String MESSAGE_USER_NOT_FOUND = "message.user.notFound";
    public static final String MESSAGE_CREATE_USER_OK = "message.user.createOk";
    public static final String MESSAGE_UPDATE_USER_OK = "message.user.updateOk";
    public static final String MESSAGE_DELETE_USER_OK = "message.user.deleteOk";
    public static final String MESSAGE_CANT_CREATE_USER = "message.user.cantCreate";
    public static final String MESSAGE_CANT_UPDATE_USER = "message.user.cantupdate";
    public static final String MESSAGE_CANT_DELETE_USER = "message.user.cantDelete";

}
