package com.ecommerce.controller;

import com.ecommerce.model.Items;
import com.ecommerce.model.JSONCart;
import com.ecommerce.model.UserItem;
import com.ecommerce.model.response.JSONResponse;
import com.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {

    private final CartService service;

    @Autowired
    public CartController(CartService service) {
        this.service = service;
    }

    @GetMapping("/check")
    public String s() {
        return "[UP]";
    }

    @PostMapping("/")
    public ResponseEntity<JSONResponse> addItem(@RequestBody UserItem<String> u) {
        JSONResponse jsonResponse = service.addItemToCart(u.getItemId(),u.getUserName());
        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity<JSONResponse> removeItem(@RequestBody  UserItem<String> u) {
        JSONResponse jsonResponse = service.removeItemFromCart(u.getItemId(),u.getUserName());
        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
    }

    @PostMapping("/items/{userName}")
    public ResponseEntity<JSONResponse> getAllCheckedOutItems(@PathVariable("userName") String userName) {
        JSONResponse<JSONCart> list = service.getAllCheckedOutItems(userName);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/purchased-items/{userName}")
    public ResponseEntity<JSONResponse> getAllPurchasedItems(@PathVariable("userName") String userName) {
        JSONResponse<List<Items>> list = service.getAllPurchasedItems(userName);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PutMapping("/purchase/{userName}")
    public ResponseEntity<JSONResponse> purchase(@PathVariable("userName") String userName) {
        JSONResponse jsonResponse = service.itemsPurchased(userName);
        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
    }

    @PostMapping("/bulk")
    public ResponseEntity<JSONResponse> addBulk(@RequestBody UserItem<List<String>> userItem) {
        final String userName = userItem.getUserName();
        userItem.getItemId().forEach(e->service.addItemToCart(e,userName));
        return new ResponseEntity<>(JSONResponse.positive(),HttpStatus.OK);
    }
}

