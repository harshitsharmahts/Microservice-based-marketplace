package com.ecommerce.model.response;

import com.ecommerce.model.Users;

import java.util.Optional;

public class LoginSignupResponseBody {

    private String status;
    private Optional<Users> body;

    public LoginSignupResponseBody() {

    }

    public LoginSignupResponseBody(String status, Users body) {
        this.status = status;
        this.body = Optional.of(body);
    }

    public String getStatus() {
        return status;
    }

    public LoginSignupResponseBody setStatus(String status) {
        this.status = status;
        return this;
    }

    public Optional<Users> getBody() {
        return body;
    }

    public LoginSignupResponseBody setBody(Users body) {
        this.body = Optional.of(body);
        return this;
    }
}
