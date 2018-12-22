package com.commerce.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@ComponentScan({
		"com.commerce.controller",
		"com.commerce.repository",
		"com.commerce.service",
		"com.commerce.config"
})
@EnableMongoRepositories("com.commerce.repository")
@SpringBootApplication
@EnableEurekaClient
public class DbUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbUserApplication.class, args);
	}
}
