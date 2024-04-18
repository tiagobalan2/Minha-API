package com.example.api.com.front;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ApiComFrontApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiComFrontApplication.class, args);
	}

}
