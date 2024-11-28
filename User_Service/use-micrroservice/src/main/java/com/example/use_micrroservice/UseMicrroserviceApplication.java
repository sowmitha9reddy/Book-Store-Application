package com.example.use_micrroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UseMicrroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UseMicrroserviceApplication.class, args);

		System.out.println("Hello User Service");
	}

}
