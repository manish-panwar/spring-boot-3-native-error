package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication(scanBasePackages = { "com.example" })
@EnableR2dbcRepositories(basePackages = { "com.example" })
public class DemoAotNativeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoAotNativeApplication.class, args);
	}
}
