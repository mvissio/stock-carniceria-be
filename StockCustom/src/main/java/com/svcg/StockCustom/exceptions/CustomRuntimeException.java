package com.svcg.StockCustom.exceptions;

public class CustomRuntimeException extends RuntimeException {

	private static final long serialVersionUID = -3465883999716291577L;
	
	public CustomRuntimeException(Throwable err) {
        super(err);
    }
	
	public CustomRuntimeException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

}
