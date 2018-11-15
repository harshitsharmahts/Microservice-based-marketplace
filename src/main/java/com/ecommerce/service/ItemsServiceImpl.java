package com.ecommerce.service;

import com.ecommerce.model.Items;
import com.ecommerce.repository.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemsServiceImpl implements ItemsService {

    private final ItemsRepository repository;

    @Autowired
    public ItemsServiceImpl(ItemsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Items addNewItem(Items item) {
        return repository.insert(item);
    }

    @Override
    public Items updateItem(Items item) {
        return repository.save(item);
    }

    @Override
    public Items deleteItem(String itemId) {
        Items item = getItem(itemId);
        repository.delete(item);
        return item;
    }

    @Override
    public List<Items> getAllItems() {
        return repository.findAll();
    }

    @Override
    public Items getItem(String itemId) {
        return repository.findById(itemId).orElse(null);
    }

    @Override
    public List<Items> getAllItems(String keyword) {
        return null;
    }

    @Override
    public List<Items> getByPage(int pageNumber) {
        int startIndex = 1*(pageNumber-1);
        int endIndex = 1*(pageNumber);

        return repository.findAll();
    }
}
