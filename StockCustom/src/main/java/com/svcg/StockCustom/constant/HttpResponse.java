package com.svcg.StockCustom.constant;

public class HttpResponse {

    public enum Status {

        OK(200, "OK"),
        CREATED(201, "Created"),
        NO_CONTENT(204, "No Content"),
        BAD_REQUEST(400, "Bad Request"),
        NOT_FOUND(404, "Not Found");
        // TODO:  COMPLETED CODE CONSTANTS!


        private final int code;
        private final String message;

        Status(int statusCode, String message) {
            this.code = statusCode;
            this.message = message;
        }


        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }
}
