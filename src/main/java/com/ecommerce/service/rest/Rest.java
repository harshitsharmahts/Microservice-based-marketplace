package com.ecommerce.service.rest;

import com.ecommerce.model.Items;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class Rest {

    private final RestTemplate restTemplate;

    @Value("${rest.item.add}")
    private String ADD_URL;

    @Value("${rest.item.get}")
    private String GET_URL;

    @Value("${rest.item.update}")
    private String UPDATE_URL;

    @Value("${rest.item.delete}")
    private String DELETE_URL;

    @Value("${rest.items.get}")
    private String GET_ITEMS_URL;


    @Autowired
    public Rest(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Items addItem(Items item) {
        HttpEntity<Items> requestEntity = new HttpEntity<>(item,getHeader());

        return restTemplate
                .exchange(this.ADD_URL, HttpMethod.POST,requestEntity,Items.class).getBody();
    }

    public Items updateItem(Items item) {
        HttpEntity<Items> entity = new HttpEntity<>(item,getHeader());

        return restTemplate
                .exchange(this.UPDATE_URL, HttpMethod.PUT,entity,Items.class)
                .getBody();
    }

    public void deleteItem(String itemId) {

        HttpEntity entity = new HttpEntity<>(getHeader());

        restTemplate
                .exchange(this.DELETE_URL,HttpMethod.DELETE,entity,Void.class,itemId)
                .getBody();

    }

    public List<Items> getItems(int page, int size) {

        HttpEntity entity = new HttpEntity(getHeader());

        return restTemplate
                .exchange(this.GET_ITEMS_URL, HttpMethod.GET, entity, new ParameterizedTypeReference<List<Items>>() {}, page, size)
                .getBody();
    }

    public Items getItem(String itemId) {

        HttpEntity entity = new HttpEntity<>(getHeader());
        return restTemplate
                .exchange(this.GET_URL,HttpMethod.GET,entity,Items.class,itemId)
                .getBody();
    }

    private HttpHeaders getHeader() {
        HttpHeaders httpHeader = new HttpHeaders();
        httpHeader.setContentType(MediaType.APPLICATION_JSON);

        return httpHeader;
    }
}
