package com.commerce.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.commerce.model.Users;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends MongoRepository<Users,String> {

    Optional<List<Users>> findByEmail(String email);
}