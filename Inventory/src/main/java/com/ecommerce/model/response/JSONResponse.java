package com.ecommerce.model.response;

public class JSONResponse<T> {

    private boolean status;
    private T body;

    public JSONResponse() {

    }

    public JSONResponse(boolean status, T body) {
        this.status = status;
        this.body = body;
    }

    public boolean getStatus() {
        return status;
    }

    public JSONResponse<T> setStatus(boolean status) {
        this.status = status;
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