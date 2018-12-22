package com.ecommerce.model;

/**
 * <p>
 *     LoginCredentials model class to hold the login credentials of a user.
 * </p>
 * @author Harshit Sharma
 */
public class LoginCredentials {

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginCredentials(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
