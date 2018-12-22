package com.ecommerce.CheckOut;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({
		"com.ecommerce.controller",
		"com.ecommerce.service",
		"com.ecommerce.CheckOut",
		"com.ecommerce.rest"
})
@SpringBootApplication
@EnableEurekaClient
public class CheckOutApplication {

	public static void main(String[] args) {
		SpringApplication.run(CheckOutApplication.class, args);
	}
}
