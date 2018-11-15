package com.ecommerce.repository;

import com.ecommerce.model.Items;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemsRepository extends MongoRepository<Items, String>{

}
