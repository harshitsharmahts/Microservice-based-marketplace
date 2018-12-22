package com.commerce.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class SpringMongoConfig {

    @Value("${mongodb.atlas.uri}")
    private String mongoURI;

    @Value("${mongodb.atlas.database}")
    private String database;

    @Bean
    public MongoClient mongoClient() {
        MongoClientURI mongoClientURI = new MongoClientURI(mongoURI);
        return new MongoClient(mongoClientURI);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(),database);
    }
}
