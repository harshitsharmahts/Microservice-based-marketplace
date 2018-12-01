package com.ecommerce.service;

import com.ecommerce.model.Items;
import com.ecommerce.model.Users;
import com.ecommerce.model.response.CartResponseBody;
import com.ecommerce.rest.Rest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final Rest rest;

    @Autowired
    public CartServiceImpl(Rest rest) {
        this.rest = rest;
    }

    @Override
    public CartResponseBody<List<String>> addItemToCart(String itemId, String userName) {
        Users u = rest.getUser(userName);
        List<String> list = u.getCheckoutList().orElse(new ArrayList<>());
        list.add(itemId);
        u.setCheckoutList(list);

        if(!rest.updateUser(u))
            return new CartResponseBody<List<String>>().setStatus(false);

        return new CartResponseBody<List<String>>()
                .setStatus(true)
                .setBody(list);
    }

    @Override
    public CartResponseBody<List<String>> removeItemFromCart(String itemId, String userName) {
        Users u = rest.getUser(userName);
        List<String> list = u.getCheckoutList().orElse(new ArrayList<>());
        list.remove(itemId);
        u.setCheckoutList(list);

        if(!rest.updateUser(u))
            return new CartResponseBody<List<String>>().setStatus(false);

        return new CartResponseBody<List<String>>()
                .setStatus(true)
                .setBody(list);
    }

    @Override
    public CartResponseBody<List<Items>> getAllCheckedOutItems(String userName) {

        Users u = rest.getUser(userName);
        List<String> list = u.getCheckoutList().orElse(new ArrayList<>());

        List<Items> listItems = new ArrayList<>();
        list.forEach((String id) ->{
            Items i = rest.getItem(id);
            listItems.add(i);
        });

        return new CartResponseBody<List<Items>>()
                .setStatus(true)
                .setBody(listItems);
    }

    @Override
    public CartResponseBody<List<Items>> getAllPurchasedItems(String userName) {

        Users u = rest.getUser(userName);

        List<String> list = u.getPurchaseHistory().orElse(new ArrayList<>());

        List<Items> listItems = new ArrayList<>();
        list.forEach(id->{
            Items i = rest.getItem(id);
            listItems.add(i);
        });

        return new CartResponseBody<List<Items>>()
                .setStatus(true)
                .setBody(listItems);
    }

    @Override
    public CartResponseBody<List<String>> itemPurchased(String itemId, String userName) {

        Users u = rest.getUser(userName);
        List<String> list = u.getPurchaseHistory().orElse(new ArrayList<>());

        list.add(itemId);
        u.setPurchaseHistory(list);

        if(!rest.updateUser(u))
            return new CartResponseBody<List<String>>().setStatus(false);

        return new CartResponseBody<List<String>>()
                .setStatus(true)
                .setBody(list);
    }
}
