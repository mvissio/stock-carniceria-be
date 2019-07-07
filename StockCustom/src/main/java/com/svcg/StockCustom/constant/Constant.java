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
    
    //Auth
    public static final String MESSAGE_EMAIL_NOT_EXISTS = "message.auth.emailExist";
    public static final String MESSAGE_CANT_RESET_PASS = "message.auth.password.cantReset";
    public static final String MESSAGE_CANT_SEND_MAIL_NEW_PASS = "message.auth.password.cantSendMail";
    
    //User
    public static final String MESSAGE_USER_EXIST = "message.user.name.userExist";
    public static final String MESSAGE_EMAIL_EXIST = "message.user.email.emailExist";
    public static final String MESSAGE_USER_NOT_FOUND = "message.user.notFound";
    public static final String MESSAGE_CANT_CREATE_USER = "message.user.cantCreate";
    public static final String MESSAGE_CANT_UPDATE_USER = "message.user.cantupdate";
    public static final String MESSAGE_CANT_DELETE_USER = "message.user.cantDelete";
    
    //Article
    public static final String MESSAGE_ARTICLE_EXISTS = "message.article.name.articleExist";
    public static final String MESSAGE_NOT_FOUND_ARTICLES = "message.article.notFounds";
    public static final String MESSAGE_NOT_FOUND_ARTICLE = "message.article.notFound";
    public static final String MESSAGE_CANT_CREATE_ARTICLE = "message.article.cantCreate";
    public static final String MESSAGE_CANT_UPDATE_ARTICLE = "message.article.cantupdate";
    public static final String MESSAGE_CANT_DELETE_ARTICLE = "message.article.cantDelete";

    //MeasurementUnit
    public static final String MESSAGE_NOT_FOUND_MEASUREMENT_UNITS = "message.measurementUnit.notFounds";
    public static final String MESSAGE_NOT_FOUND_MEASUREMENT_UNIT = "message.measurementUnit.notFound";
    public static final String MESSAGE_CANT_CREATE_MEASUREMENT_UNIT = "message.measurementUnit.cantCreate";
    public static final String MESSAGE_CANT_UPDATE_MEASUREMENT_UNIT = "message.measurementUnit.cantupdate";
    public static final String MESSAGE_CANT_DELETE_MEASUREMENT_UNIT = "message.measurementUnit.cantDelete";

    //Client
    public static final String MESSAGE_CLIENT_EXISTS = "message.client.name.userExist";
    public static final String MESSAGE_NOT_FOUND_CLIENTS = "message.client.notFounds";
    public static final String MESSAGE_NOT_FOUND_CLIENT = "message.client.notFound";
    public static final String MESSAGE_CANT_CREATE_CLIENT = "message.client.cantCreate";
    public static final String MESSAGE_CANT_UPDATE_CLIENT = "message.client.cantupdate";
    public static final String MESSAGE_CANT_DELETE_CLIENT = "message.client.cantDelete";
    
    //Provider
    public static final String MESSAGE_PROVIDER_EXISTS = "message.provider.name.userExist";
    public static final String MESSAGE_NOT_FOUND_PROVIDERS = "message.provider.notFounds";
    public static final String MESSAGE_NOT_FOUND_PROVIDER = "message.provider.notFound";
    public static final String MESSAGE_CANT_CREATE_PROVIDER = "message.provider.cantCreate";
    public static final String MESSAGE_CANT_UPDATE_PROVIDER = "message.provider.cantupdate";
    public static final String MESSAGE_CANT_DELETE_PROVIDER = "message.provider.cantDelete";
    
    //Category
    public static final String MESSAGE_NOT_FOUND_CATEGORIES = "message.category.notFounds";
    public static final String MESSAGE_NOT_FOUND_CATEGORY = "message.category.notFound";
    public static final String MESSAGE_CANT_CREATE_CATEGORY = "message.category.cantCreate";
    public static final String MESSAGE_CANT_UPDATE_CATEGORY = "message.category.cantupdate";
    public static final String MESSAGE_CANT_DELETE_CATEGORY = "message.category.cantDelete";
    public static final String MESSAGE_CATEGORY_EXISTS = "message.category.categoryExist";
    
    //Operation
    public static final String MESSAGE_NOT_FOUND_OPERATIONS = "message.operation.notFounds";
    public static final String MESSAGE_NOT_FOUND_OPERATION = "message.operation.notFound";
    public static final String MESSAGE_CANT_CREATE_OPERATION = "message.operation.cantCreate";
    public static final String MESSAGE_CANT_UPDATE_OPERATION = "message.operation.cantupdate";
    public static final String MESSAGE_CANT_DELETE_OPERATION = "message.operation.cantDelete";
    
    //Rol
    public static final String MESSAGE_NOT_FOUND_ROLE = "message.rol.notFounds";
    public static final String MESSAGE_CANT_CREATE_ROL = "message.rol.cantCreate";
    
    //Box
    public static final String MESSAGE_NOT_FOUND_BOXS = "message.box.notFounds";
    public static final String MESSAGE_NOT_FOUND_BOXS_OPEN = "message.box.notFoundsOpen";
    public static final String MESSAGE_NOT_FOUND_BOX = "message.box.notFound";
    public static final String MESSAGE_CANT_CREATE_BOX = "message.box.cantCreate";
    public static final String MESSAGE_CANT_UPDATE_BOX = "message.box.cantupdate";
    public static final String MESSAGE_CANT_DELETE_BOX = "message.box.cantDelete";

}