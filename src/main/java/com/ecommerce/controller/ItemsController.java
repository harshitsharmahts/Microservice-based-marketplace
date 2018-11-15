package com.ecommerce.controller;

import com.ecommerce.model.Items;
import com.ecommerce.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rest")
public class ItemsController {

    private final ItemsService service;

    @Autowired
    public ItemsController(@Qualifier("itemsServiceImpl") ItemsService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<Items> getAllItems(){
        return service.getAllItems();
    }

    @GetMapping("/item/{id}")
    public Items getItem(@PathVariable("id") String itemId) {
        return service.getItem(itemId);
    }

    @PostMapping("/add")
    public Items addNewItem(@RequestBody Items item) {
        return service.addNewItem(item);
    }

    @PostMapping("/update")
    public Items updateItem(@RequestBody Items item) {
        return service.updateItem(item);
    }

    @DeleteMapping("/delete")
    public Items deleteItem(String itemId) {
        return service.deleteItem(itemId);
    }

    @GetMapping("/all/{page-number}")
    public List<Items> getByPage(@PathVariable("page-number") int pageNumber) {
        return service.getByPage(pageNumber);
    }
}
