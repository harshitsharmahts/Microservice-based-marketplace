package com.ecommerce.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@ComponentScan({
		"com.ecommerce.repository",
		"com.ecommerce.controller",
		"com.ecommerce.service",
		"com.ecommerce.config"
})
@EnableMongoRepositories("com.ecommerce.repository")
@SpringBootApplication
@EnableEurekaClient
public class DbItemApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbItemApplication.class, args);
	}
}
