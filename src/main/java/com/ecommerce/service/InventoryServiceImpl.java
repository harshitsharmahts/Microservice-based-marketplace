package com.ecommerce.service;

import com.ecommerce.model.response.JSONResponse;
import com.ecommerce.model.Items;
import com.ecommerce.service.amazon.AmazonS3Service;
import com.ecommerce.service.rest.Rest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final Rest rest;
    private final AmazonS3Service s3Service;

    @Autowired
    public InventoryServiceImpl(AmazonS3Service s3Service, Rest rest) {
        this.rest = rest;
        this.s3Service = s3Service;
    }

    @Override
    public JSONResponse<Items> addItem(
            String title,
            String description,
            Integer price,
            Short itemCount,
            MultipartFile image,
            boolean suspended
    ) {
        Items item = buildItem(title, description, price, itemCount, image, suspended);
        return addItem(item);
    }

    @Override
    public JSONResponse<Items> updateItem(
            String title,
            String description,
            Integer price,
            Short itemCount,
            MultipartFile image,
            boolean suspended
    ) {
        Items item = buildItem(title, description, price, itemCount, image, suspended);
        return updateItem(item);
    }

    @Override
    public JSONResponse<Void> deleteItem(String itemId) {

        Items respItem = rest.getItem(itemId);

        if(respItem != null) {
            if (respItem.getImageUrl() != null)
                s3Service.deleteFileFromS3Bucket(respItem.getImageUrl());

            rest.deleteItem(itemId);
        }
        return new JSONResponse<Void>()
                .setStatus(true);
    }

    @Override
    public JSONResponse suspendItem(String itemId) {
        Items item = rest.getItem(itemId);
        item.setSuspended(true);
        updateItem(item);
        return new JSONResponse().setStatus(true);
    }

    @Override
    public JSONResponse unSuspendItem(String itemId) {
        Items item = rest.getItem(itemId);
        item.setSuspended(false);
        updateItem(item);
        return new JSONResponse().setStatus(true);
    }


    @Override
    public JSONResponse<List<Items>> getAll(Integer page, Integer size) {

        List<Items> list = rest.getItems(page,size);

        return new JSONResponse<List<Items>>()
                .setStatus(true)
                .setBody(list);
    }

    @Override
    public JSONResponse<Items> getItem(String itemId) {

        Items item = rest.getItem(itemId);

        return new JSONResponse<Items>()
                .setStatus(true)
                .setBody(item);
    }


    private JSONResponse<Items> addItem(Items item) {

        Items i = rest.addItem(item);

        return new JSONResponse<Items>()
                .setStatus(true)
                .setBody(i);
    }


    private Items buildItem(String title, String description, Integer price, Short itemCount, MultipartFile image, boolean suspended) {
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
        return item;
    }

    private JSONResponse<Items> updateItem(Items item) {
        Items newItem = rest.updateItem(item);

        return new JSONResponse<Items>()
                .setStatus(true)
                .setBody(newItem);
    }
}
