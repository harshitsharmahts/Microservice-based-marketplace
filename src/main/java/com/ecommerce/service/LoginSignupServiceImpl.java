package com.ecommerce.service;

import com.ecommerce.model.LoginCredentials;
import com.ecommerce.model.Users;
import com.ecommerce.model.response.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoginSignupServiceImpl implements LoginSignupService {

    private static final String baseUrl = "http://localhost:8081/user";
    private final RestTemplate restTemplate;

    @Autowired
    public LoginSignupServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Status login(LoginCredentials loginCredentials) {

        String url = baseUrl+"/get/{email}";
        HttpHeaders httpHeaders = getHeader();

        Users u = restTemplate
                .exchange(url,HttpMethod.GET,new HttpEntity<>(httpHeaders),Users.class,loginCredentials.getEmail())
                .getBody();

        if(u != null && u.getPassword().equals(loginCredentials.getPassword())) {
            return new Status(true,u.setPassword(""));
        }

        return new Status().setSuccess(false);
    }

    @Override
    public Status signup(Users user) {
        String url = baseUrl+"/get/{email}";
        HttpHeaders httpHeaders = getHeader();

        Users u = restTemplate
                .exchange(url,HttpMethod.GET,new HttpEntity<>(httpHeaders),Users.class,user.getEmail())
                .getBody();
        if(null == u){
            HttpEntity<Users> httpEntity = new HttpEntity<>(user,httpHeaders);
            Users newUser = restTemplate
                    .exchange(url, HttpMethod.POST,httpEntity,Users.class)
                    .getBody();
            return new Status(true,newUser);
        }
        return new Status().setSuccess(false);
    }

    @Override
    public Status loginAsManager(LoginCredentials loginCredentials) {
        return null;
    }

    private HttpHeaders getHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
