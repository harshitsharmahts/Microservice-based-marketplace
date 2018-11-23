package com.ecommerce.model.response;

import com.ecommerce.model.Users;

public class LoginSignupResponseBody {

    private String status;
    private Users body;

    public LoginSignupResponseBody() {

    }

    public LoginSignupResponseBody(String status, Users body) {
        this.status = status;
        this.body = body;
    }

    public String getStatus() {
        return status;
    }

    public LoginSignupResponseBody setStatus(String status) {
        this.status = status;
        return this;
    }

    public Users getBody() {
        return body;
    }

    public LoginSignupResponseBody setBody(Users body) {
        this.body = body;
        return this;
    }
}
