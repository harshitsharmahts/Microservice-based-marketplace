package com.ecommerce.service;

import com.ecommerce.model.Items;

import java.util.List;

public interface ItemsService {
    Items addNewItem(Items item);
    Items updateItem(Items item);
    Items deleteItem(String itemId);
    List<Items> getAllItems();
    Items getItem(String itemId);
    List<Items> getAllItems(String keyword);
    List<Items> getByPage(int pageNumber);
}
