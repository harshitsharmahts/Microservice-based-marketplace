package com.ecommerce.controller;

import com.ecommerce.model.Items;
import com.ecommerce.model.response.CartResponseBody;
import com.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService service;

    @Autowired
    public CartController(CartService service) {
        this.service = service;
    }

    @PostMapping("/")
    public ResponseEntity<CartResponseBody> addItem(String itemId, String userName) {
        CartResponseBody<List<String>> list = service.addItemToCart(itemId,userName);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity<CartResponseBody> removeItem(String itemId, String userName) {
        CartResponseBody<List<String>> list = service.removeItemFromCart(itemId, userName);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/items")
    public ResponseEntity<CartResponseBody> getAllCheckedOutItems(String userName) {
        CartResponseBody<List<Items>> list = service.getAllCheckedOutItems(userName);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/purchased-items")
    public ResponseEntity<CartResponseBody> getAllPurchasedItems(String userName) {
        CartResponseBody<List<Items>> list = service.getAllPurchasedItems(userName);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/purchase")
    public ResponseEntity<CartResponseBody> itemPurchased(String itemId, String userName) {
        CartResponseBody<List<String>> list = service.itemPurchased(itemId, userName);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
