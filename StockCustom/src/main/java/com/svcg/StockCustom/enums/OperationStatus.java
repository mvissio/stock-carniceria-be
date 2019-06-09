package com.svcg.StockCustom.enums;

public enum OperationStatus {
    CANCELED("CANCELED"),
    SOLD("SOLD"),
    PURCHASED("PURCHASED");

    private String nameOperation;


    OperationStatus(String nameOperation) {
        this.nameOperation = nameOperation;
    }
    
    public String nameOperation(){
    	return nameOperation;
    }

    
}
