package com.ecommerce.model;

public class UserItem<T> {

    private String userName;
    private T itemId;

    public UserItem() {

    }

    public UserItem(T itemId, String userName) {
        this.itemId = itemId;
        this.userName = userName;
    }

    public T getItemId() {
        return itemId;
    }

    public void setItemId(T itemId) {
        this.itemId = itemId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
