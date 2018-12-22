package com.ecommerce.controller;

import com.ecommerce.model.Items;
import com.ecommerce.model.response.JSONResponse;
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

    @GetMapping("/status")
    public String statusCheck() {
        return "Inventory Service\t\t-----\t\t [UP]";
    }

    @GetMapping("/{page}/{size}")
    public ResponseEntity<JSONResponse<List<Items>>> getAll(@PathVariable("page") Integer page,
                                                            @PathVariable("size") Integer size) {
        JSONResponse<List<Items>> list = service.getAll(page,size);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JSONResponse<Items>> getItem(@PathVariable("id") String id) {
        JSONResponse<Items> i = service.getItem(id);
        return  new ResponseEntity<>(i, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<JSONResponse<Items>> addItem(
            String title,
            String description,
            Integer price,
            Short itemCount,
            MultipartFile image,
            boolean suspended
    ) {

        JSONResponse<Items> i = service.addItem(
                title,
                description,
                price,
                itemCount,
                image,
                suspended
        );

        return new ResponseEntity<>(i,HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<JSONResponse<Items>> updateItem(
            String title,
            String description,
            Integer price,
            Short itemCount,
            MultipartFile image,
            boolean suspended
    ) {
        JSONResponse<Items> i = service.updateItem(
                title,
                description,
                price,
                itemCount,
                image,
                suspended
        );

        return new ResponseEntity<>(i,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable("id") String id) {
        service.deleteItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/suspend/{id}")
    public ResponseEntity<JSONResponse> suspendItem(@PathVariable("id") String id) {
        JSONResponse j = service.suspendItem(id);
        return new ResponseEntity<>(j, HttpStatus.CREATED);
    }

    @PutMapping("/un-suspend/{id}")
    public ResponseEntity<JSONResponse> unSuspendItem(@PathVariable("id") String id) {
        JSONResponse j = service.unSuspendItem(id);
        return new ResponseEntity<>(j, HttpStatus.CREATED);
    }
}
