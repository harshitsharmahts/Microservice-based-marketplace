package com.ecommerce.service;

import com.ecommerce.model.Items;
import com.ecommerce.model.JSONCart;
import com.ecommerce.model.response.JSONResponse;

import java.util.List;

public interface CartService {

    JSONResponse addItemToCart(String itemId, String userName);
    JSONResponse removeItemFromCart(String itemId, String userName);
    JSONResponse<JSONCart> getAllCheckedOutItems(String userName);
    JSONResponse<List<Items>> getAllPurchasedItems(String userName);
    JSONResponse itemsPurchased(String userName);
    JSONResponse getInvoice(String userName);
}
