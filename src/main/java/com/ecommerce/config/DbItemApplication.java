package com.ecommerce.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("com.ecommerce.repository")
@ComponentScan({
		"com.ecommerce.repository",
		"com.ecommerce.controller",
		"com.ecommerce.service"
})
public class DbItemApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbItemApplication.class, args);
	}
}
