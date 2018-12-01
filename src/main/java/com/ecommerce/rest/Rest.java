package com.ecommerce.rest;

import com.ecommerce.model.Items;
import com.ecommerce.model.Users;
import com.ecommerce.model.constant.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Rest {

    private final RestTemplate restTemplate;

    @Autowired
    public Rest(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Users getUser(String u) {
        return restTemplate
                .exchange(C.REST.GET_USER, HttpMethod.GET, new HttpEntity<>(getHeaders()), Users.class, u)
                .getBody();
    }

    public boolean updateUser(Users user) {
        HttpEntity<Users> httpEntity = new HttpEntity<>(user,getHeaders());
        Users u = restTemplate.exchange(C.REST.PUT_USER, HttpMethod.PUT, httpEntity, Users.class).getBody();
        if(u == null)
            return false;
        return true;
    }

    public Items getItem(String itemId) {
        return restTemplate.exchange(C.REST.GET_ITEM, HttpMethod.GET,new HttpEntity<>(getHeaders()),Items.class,itemId).getBody();
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
