package com.ecommerce.LoginSignup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({
		"com.ecommerce.controller",
		"com.ecommerce.service",
		"com.ecommerce.LoginSignup"
})
@SpringBootApplication
public class LoginSignupApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginSignupApplication.class, args);
	}
}
