package com.ecommerce.model.response;

public class CartResponseBody<T> {

    private Boolean status;
    private T body;

    public CartResponseBody() {
    }

    public CartResponseBody(Boolean status, T body) {
        this.status = status;
        this.body = body;
    }

    public Boolean getStatus() {
        return status;
    }

    public CartResponseBody<T> setStatus(Boolean status) {
        this.status = status;
        return this;
    }

    public T getBody() {
        return body;
    }

    public CartResponseBody<T> setBody(T body) {
        this.body = body;
        return this;
    }
}
