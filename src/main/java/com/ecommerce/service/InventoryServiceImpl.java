package com.ecommerce.service;

import com.ecommerce.model.constant.C;
import com.ecommerce.model.response.ItemResponseBody;
import com.ecommerce.model.Items;
import com.ecommerce.service.amazon.AmazonS3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final RestTemplate restTemplate;
    private final AmazonS3Service s3Service;

    @Autowired
    public InventoryServiceImpl(RestTemplate restTemplate, AmazonS3Service s3Service) {
        this.restTemplate = restTemplate;
        this.s3Service = s3Service;
    }

    private ItemResponseBody<Items> addItem(Items item) {
        HttpHeaders headers = getHeader();
        HttpEntity<Items> entity = new HttpEntity<>(item,headers);
        Items newItem = restTemplate
                .exchange(C.REST.POST_ADD_URL, HttpMethod.POST,entity,Items.class)
                .getBody();

        return new ItemResponseBody<Items>()
                .setStatus(C.STATUS.ADDED)
                .setBody(newItem);
    }

    @Override
    public ItemResponseBody<Items> addItem(
            String title,
            String description,
            Integer price,
            Short itemCount,
            MultipartFile image,
            boolean suspended
    ) {
        Items item = new Items()
                .setTitle(title)
                .setDescription(description)
                .setPrice(price)
                .setItemCount(itemCount)
                .setSuspended(suspended);

        String imageUrl = s3Service.uploadFileTos3bucket(image);

        return addItem(item.setImageUrl(imageUrl));
    }

    private ItemResponseBody<Items> updateItem(Items item) {
        HttpHeaders headers = getHeader();
        HttpEntity<Items> entity = new HttpEntity<>(item,headers);
        Items newItem = restTemplate
                .exchange(C.REST.PUT_UPDATE_URL, HttpMethod.PUT,entity,Items.class)
                .getBody();

        return new ItemResponseBody<Items>()
                .setStatus(C.STATUS.UPDATED)
                .setBody(newItem);
    }

    @Override
    public ItemResponseBody<Items> updateItem(
            String title,
            String description,
            Integer price,
            Short itemCount,
            MultipartFile image,
            boolean suspended
    ) {
        Items item = new Items()
                .setTitle(title)
                .setDescription(description)
                .setPrice(price)
                .setItemCount(itemCount)
                .setSuspended(suspended);
        if(image != null){
            String imageUrl = s3Service.uploadFileTos3bucket(image);
            item.setImageUrl(imageUrl);
        }
        return updateItem(item);
    }

    @Override
    public ItemResponseBody<Void> deleteItem(String itemId) {
        HttpHeaders header = getHeader();
        HttpEntity entity = new HttpEntity<>(header);

        Items respItem = getItem(itemId).getBody();
        s3Service.deleteFileFromS3Bucket(respItem.getImageUrl());

        restTemplate
                .exchange(C.REST.DELETE_URL,HttpMethod.DELETE,entity,Void.class,itemId)
                .getBody();

        return new ItemResponseBody<Void>()
                .setStatus(C.STATUS.TRUE);
    }

    @Override
    public Boolean suspendItem(String itemId) {
        Items item = getItem(itemId).getBody();
        item.setSuspended(true);
        updateItem(item);
        return true;
    }

    @Override
    public Boolean unSuspendItem(String itemId) {
        Items item = getItem(itemId).getBody();
        item.setSuspended(true);
        updateItem(item);
        return true;
    }


    @Override
    public ItemResponseBody<List<Items>> getAll(Integer page, Integer size) {
        HttpHeaders headers = getHeader();
        HttpEntity entity = new HttpEntity(headers);
        List<Items> list = restTemplate
                .exchange(C.REST.GET + "{page}/{size}", HttpMethod.GET, entity, new ParameterizedTypeReference<List<Items>>() {}, page, size)
                .getBody();
        return new ItemResponseBody<List<Items>>()
                .setStatus(C.STATUS.TRUE)
                .setBody(list);
    }

    @Override
    public ItemResponseBody<Items> getItem(String itemId) {
        HttpHeaders headers = getHeader();
        HttpEntity entity = new HttpEntity<>(headers);
        Items item = restTemplate
                .exchange(C.REST.GET+"{id}",HttpMethod.GET,entity,Items.class,itemId)
                .getBody();
        return new ItemResponseBody<Items>()
                .setStatus(C.STATUS.TRUE)
                .setBody(item);
    }

    private HttpHeaders getHeader() {
        HttpHeaders httpHeader = new HttpHeaders();
        httpHeader.setContentType(MediaType.APPLICATION_JSON);

        return httpHeader;
    }
}
