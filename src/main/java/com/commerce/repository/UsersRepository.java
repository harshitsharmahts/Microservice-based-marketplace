package com.commerce.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.commerce.model.Users;

public interface UsersRepository extends MongoRepository<Users,String> {

}