package com.ecommerce.model.response;

public class JSONResponse<T> {

    private String message;
    private T body;

    public JSONResponse() {

    }

    public JSONResponse(String status, T body) {
        this.message = status;
        this.body = body;
    }

    public String getMessage() {
        return message;
    }

    public JSONResponse<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getBody() {
        return body;
    }

    public JSONResponse<T> setBody(T body) {
        this.body = body;
        return this;
    }
}
