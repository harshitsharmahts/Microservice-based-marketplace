package com.ecommerce.Inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({
		"com.ecommerce.controller",
		"com.ecommerce.service",
		"com.ecommerce.Inventory",
		"com.ecommerce.service.amazon"
})
@SpringBootApplication
public class InventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}
}
