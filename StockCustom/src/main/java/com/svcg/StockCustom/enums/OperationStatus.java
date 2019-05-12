package com.svcg.StockCustom.enums;

public enum OperationStatus {
    CANCELED("CANCELED"),
    BUY("BUY");

    private String nameOperation;


    OperationStatus(String nameOperation) {
        this.nameOperation = nameOperation;
    }
    
    public String nameOperation(){
    	return nameOperation;
    }

    
}
