package com.ecommerce.service;

import com.ecommerce.model.Items;
import com.ecommerce.model.JSONCart;
import com.ecommerce.model.Users;
import com.ecommerce.model.response.JSONResponse;
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
    public JSONResponse addItemToCart(String itemId, String userName) {
        Users u = rest.getUser(userName);

        if(u == null)
            return JSONResponse.negative();

        List<String> list = u.getCheckoutList().orElse(new ArrayList<>());

        if(!list.contains(itemId)) {
            list.add(itemId);
            u.setCheckoutList(list);

            if(!rest.updateUser(u))
                return JSONResponse.negative();
        }

        return JSONResponse.positive();

    }

    @Override
    public JSONResponse removeItemFromCart(String itemId, String userName) {
        Users u = rest.getUser(userName);

        if(u == null)
            return JSONResponse.negative();

        List<String> list = u.getCheckoutList().orElse(new ArrayList<>());

        if(list.contains(itemId)) {
            list.remove(itemId);
            u.setCheckoutList(list);

            if (!rest.updateUser(u))
                return JSONResponse.negative();
        }

        return JSONResponse.positive();
    }

    @Override
    public JSONResponse<JSONCart> getAllCheckedOutItems(String userName) {

        Users u = rest.getUser(userName);

        if( u == null)
            return new JSONResponse<JSONCart>().setStatus(false);

        List<String> list = u.getCheckoutList().orElse(new ArrayList<>());

        List<Items> listItems = new ArrayList<>();
        list.forEach((String id) ->{
            Items i = rest.getItem(id);
            listItems.add(i);
        });

        JSONCart jsonCart = new JSONCart().setItems(listItems).calculateInvoice();

        return new JSONResponse<JSONCart>()
                .setStatus(true)
                .setBody(jsonCart);
    }

    @Override
    public JSONResponse<List<Items>> getAllPurchasedItems(String userName) {

        Users u = rest.getUser(userName);

        if( u == null)
            return new JSONResponse<List<Items>>().setStatus(false);

        List<String> list = u.getPurchaseHistory().orElse(new ArrayList<>());

        List<Items> listItems = new ArrayList<>();
        list.forEach(id->{
            Items i = rest.getItem(id);
            listItems.add(i);
        });

        return new JSONResponse<List<Items>>()
                .setStatus(true)
                .setBody(listItems);
    }

    @Override
    public JSONResponse itemsPurchased(String userName) {
        Users u = rest.getUser(userName);

        if(u == null)
            return JSONResponse.negative();

        List<String> checkoutList = u.getCheckoutList().orElse(new ArrayList<>());

        if(checkoutList.size() > 0) {
            List<String> purchaseList = u.getPurchaseHistory().orElse(new ArrayList<>());
            purchaseList.addAll(checkoutList);
            checkoutList = new ArrayList<>();

            u.setCheckoutList(checkoutList);
            u.setPurchaseHistory(purchaseList);


            if (!rest.updateUser(u))
                return JSONResponse.negative();
        }

        return JSONResponse.positive();
    }

    @Override
    public JSONResponse getInvoice(String userName) {
        return null;
    }
}
