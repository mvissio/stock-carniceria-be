package com.svcg.StockCustom.model;

public class ResponseModel<T> {

    private Number code;
    private String messages = "";
    private String description = "";
    private T result;

    public ResponseModel() {
    }

    public ResponseModel(Number code, String messages) {
        this.code = code;
        this.messages = messages;
    }

    public ResponseModel(Number code, String messages, String description) {
        this.code = code;
        this.messages = messages;
        this.description = description;
    }

    public Number getCode() {
        return code;
    }

    public void setCode(Number code) {
        this.code = code;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}

