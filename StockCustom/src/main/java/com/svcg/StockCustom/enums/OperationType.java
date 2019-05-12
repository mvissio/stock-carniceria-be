package com.svcg.StockCustom.enums;

public enum OperationType {
    SALE("SALE"),
    BUY("BUY");

    private String nameOperation;


    OperationType(String nameOperation) {
        this.nameOperation = nameOperation;
    }
    
    public String nameOperation(){
    	return nameOperation;
    }

    
}
