package com.ecommerce.model.response;

public class JSONResponse<T> {

    private Boolean status;
    private T body;

    public JSONResponse() {
    }

    public JSONResponse(Boolean status, T body) {
        this.status = status;
        this.body = body;
    }

    public Boolean getStatus() {
        return status;
    }

    public JSONResponse<T> setStatus(Boolean status) {
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

    public static JSONResponse positive() {
        return new JSONResponse().setStatus(true);
    }

    public static JSONResponse negative() {
        return new JSONResponse().setStatus(false);
    }
}
