package com.ecommerce.service;

import com.ecommerce.model.Items;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ItemsService {
    Items addNewItem(Items item);
    Items updateItem(Items item);
    Items deleteItem(String itemId);
    List<Items> getAllItems(int page, int size);
    Items getItem(String itemId);
    List<Items> getAllItems(String keyword);
//    List<Items> getByPage(Pageable pageable);
}
