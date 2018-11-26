package com.ecommerce.model.response;

public class ItemResponseBody<T> {

    private String status;
    private T body;

    public ItemResponseBody() {

    }

    public ItemResponseBody(String status, T body) {
        this.status = status;
        this.body = body;
    }

    public String getStatus() {
        return status;
    }

    public ItemResponseBody<T> setStatus(String status) {
        this.status = status;
        return this;
    }

    public T getBody() {
        return body;
    }

    public ItemResponseBody<T> setBody(T body) {
        this.body = body;
        return this;
    }
}