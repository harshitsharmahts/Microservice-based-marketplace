package com.ecommerce.service;

import com.ecommerce.model.Items;
import com.ecommerce.model.response.CartResponseBody;

import java.util.List;

public interface CartService {

    CartResponseBody<List<String>> addItemToCart(String itemId, String userName);
    CartResponseBody<List<String>> removeItemFromCart(String itemId, String userName);
    CartResponseBody<List<Items>> getAllCheckedOutItems(String userName);
    CartResponseBody<List<Items>> getAllPurchasedItems(String userName);
    CartResponseBody<List<String>> itemPurchased(String itemId, String userName);
}
