package com.ecommerce.service;

import com.ecommerce.model.response.ItemResponseBody;
import com.ecommerce.model.Items;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface InventoryService {

    ItemResponseBody<Items> getItem(String itemId);
    Boolean suspendItem(String itemId);
    Boolean unSuspendItem(String itemId);
    ItemResponseBody<List<Items>> getAll(Integer page, Integer size);
    ItemResponseBody<Void> deleteItem(String itemId);
    ItemResponseBody<Items> addItem(String title, String description, Integer price, Short itemCount, MultipartFile image, boolean suspended);
    ItemResponseBody<Items> updateItem(String title, String description, Integer price, Short itemCount, MultipartFile image, boolean suspended);
}
