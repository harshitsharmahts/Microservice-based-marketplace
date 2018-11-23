package com.ecommerce.controller;

import com.ecommerce.model.LoginCredentials;
import com.ecommerce.model.Users;
import com.ecommerce.model.response.Status;
import com.ecommerce.service.LoginSignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginSignupController {

    private final LoginSignupService service;

    @Autowired
    public LoginSignupController(LoginSignupService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<Status> login (@RequestBody LoginCredentials loginCredentials) {
        Status s = service.login(loginCredentials);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<Status> signup (@RequestBody Users user) {
        Status s = service.signup(user);
        return new ResponseEntity<>(s,HttpStatus.OK);
    }

    @PostMapping("/login-as-manager")
    public ResponseEntity<Status> loginAsManager(LoginCredentials loginCredentials) {
        Status b = service.loginAsManager(loginCredentials);
        return new ResponseEntity<>(b, HttpStatus.OK);
    }
}
