package com.ecommerce.rest;

import com.ecommerce.model.Items;
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
    private String GET_USER_URL;

    @Value("${rest.item.get}")
    private String GET_ITEM_URL;

    @Value("${rest.user.update}")
    private String UPDATE_USER_URL;

    @Value("${rest.item.update}")
    private String UPDATE_ITEM_URL;

    @Autowired
    public Rest(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Users getUser(String u) {
        return restTemplate
                .exchange(this.GET_USER_URL, HttpMethod.GET, new HttpEntity<>(getHeaders()), Users.class, u)
                .getBody();
    }

    public boolean updateUser(Users user) {
        HttpEntity<Users> httpEntity = new HttpEntity<>(user,getHeaders());
        Users u = restTemplate.exchange(this.UPDATE_USER_URL, HttpMethod.PUT, httpEntity, Users.class).getBody();
        if(u == null)
            return false;
        return true;
    }

    public Items getItem(String itemId) {
        return restTemplate.exchange(this.GET_ITEM_URL, HttpMethod.GET,new HttpEntity<>(getHeaders()),Items.class,itemId).getBody();
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
