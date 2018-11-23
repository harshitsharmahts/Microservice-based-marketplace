package com.ecommerce.service;


import com.ecommerce.model.LoginCredentials;
import com.ecommerce.model.Users;
import com.ecommerce.model.response.Status;

public interface LoginSignupService {
    Status login(LoginCredentials loginCredentials);
    Status signup(Users user);
    Status loginAsManager(LoginCredentials loginCredentials);
}
