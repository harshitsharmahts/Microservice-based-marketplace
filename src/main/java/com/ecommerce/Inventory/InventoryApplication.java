package com.ecommerce.Inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({
		"com.ecommerce.controller",
		"com.ecommerce.service",
		"com.ecommerce.service.amazon",
		"com.ecommerce.Inventory"
})
@SpringBootApplication
@EnableEurekaClient
public class InventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}
}
