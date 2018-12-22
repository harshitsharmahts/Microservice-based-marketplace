package com.ecommerce.service.rest;

import com.ecommerce.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Rest {

    private final RestTemplate restTemplate;

    @Value("${rest.user.get}")
    private String USER_GET_URL;

    @Value("${rest.user.add}")
    private String USER_POST_URL;

    @Autowired
    public Rest(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Users getUser(String email) {




        Users u = restTemplate
                .exchange(USER_GET_URL, HttpMethod.GET, new HttpEntity<>(getHeader()), Users.class, email)
                .getBody();

        return u;

    }

    public Users addUser(Users user) {


        HttpEntity<Users> httpEntity = new HttpEntity<>(user,getHeader());
        return restTemplate
                .exchange(USER_POST_URL, HttpMethod.POST,httpEntity,Users.class)
                .getBody();
    }


    private HttpHeaders getHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
