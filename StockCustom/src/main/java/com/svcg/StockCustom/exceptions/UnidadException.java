package com.svcg.StockCustom.exceptions;

public class UnidadException extends Exception {

    public UnidadException() {
    }

    public UnidadException(String message) {
        super(message);
    }

    public UnidadException(String message, Throwable cause) {
        super(message, cause);
    }
}
