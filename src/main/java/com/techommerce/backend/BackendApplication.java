package com.techommerce.backend;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import com.techommerce.backend.serviceImpl.ImageServiceImpl;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class BackendApplication {

	public static void main(String[] args) {
		new File(ImageServiceImpl.imageDirectory).mkdir();
		SpringApplication.run(BackendApplication.class, args);
	}

}
