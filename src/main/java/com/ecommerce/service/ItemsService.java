package com.ecommerce.service;

import com.ecommerce.model.Items;

import java.util.List;

public interface ItemsService {
    Items addItem(Items item);
    Items updateItem(Items item);
    Items deleteItem(String itemId);
    List<Items> getAllItems(int page, int size);
    Items getItem(String itemId);
    List<Items> getAllItems(String keyword);
}
