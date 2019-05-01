package com.svcg.StockCustom.exceptions;

public class RubroException extends Exception {

    public RubroException() {

    }

    public RubroException(String message) {
        super(message);
    }

    public RubroException(String message, Throwable cause) {
        super(message, cause);
    }
}
