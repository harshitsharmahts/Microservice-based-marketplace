package com.ecommerce.service;

import com.ecommerce.model.response.JSONResponse;
import com.ecommerce.model.Items;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface InventoryService {

    JSONResponse<Items> getItem(String itemId);
    JSONResponse suspendItem(String itemId);
    JSONResponse unSuspendItem(String itemId);
    JSONResponse<List<Items>> getAll(Integer page, Integer size);
    JSONResponse<Void> deleteItem(String itemId);
    JSONResponse<Items> addItem(String title, String description, Integer price, Short itemCount, MultipartFile image, boolean suspended);
    JSONResponse<Items> updateItem(String title, String description, Integer price, Short itemCount, MultipartFile image, boolean suspended);
}
