package com.ecommerce.controller;

import com.ecommerce.model.Items;
import com.ecommerce.model.response.ItemResponseBody;
import com.ecommerce.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class InventoryController {

    private final InventoryService service;


    @Autowired
    public InventoryController(@Qualifier("inventoryServiceImpl") InventoryService service) {
        this.service = service;
    }

    @GetMapping("/items/{page}/{size}")
    public ResponseEntity<ItemResponseBody<List<Items>>> getAll(@PathVariable("page") Integer page,
                                                               @PathVariable("size") Integer size) {
        ItemResponseBody<List<Items>> list = service.getAll(page,size);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<ItemResponseBody<Items>> getItem(@PathVariable("id") String id) {
        ItemResponseBody<Items> i = service.getItem(id);
        return  new ResponseEntity<>(i, HttpStatus.OK);
    }

    @PostMapping("/item")
    public ResponseEntity<ItemResponseBody<Items>> addItem(
            String title,
            String description,
            Integer price,
            Short itemCount,
            MultipartFile image,
            boolean suspended
    ) {

        ItemResponseBody<Items> i = service.addItem(
                title,
                description,
                price,
                itemCount,
                image,
                suspended
        );

        return new ResponseEntity<>(i,HttpStatus.OK);
    }

    @PutMapping("/item")
    public ResponseEntity<ItemResponseBody<Items>> updateItem(
            String title,
            String description,
            Integer price,
            Short itemCount,
            MultipartFile image,
            boolean suspended
    ) {
        ItemResponseBody<Items> i = service.updateItem(
                title,
                description,
                price,
                itemCount,
                image,
                suspended
        );

        return new ResponseEntity<>(i,HttpStatus.CREATED);
    }

    @DeleteMapping("/item/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable("id") String id) {
        service.deleteItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/item/suspend/{id}")
    public ResponseEntity<Boolean> suspendItem(@PathVariable("id") String id) {
        Boolean b = service.suspendItem(id);
        return new ResponseEntity<>(b, HttpStatus.CREATED);
    }

    @PutMapping("/item/un-suspend/{id}")
    public ResponseEntity<Boolean> unSuspendItem(@PathVariable("id") String id) {
        Boolean b = service.unSuspendItem(id);
        return new ResponseEntity<>(b, HttpStatus.CREATED);
    }
}
