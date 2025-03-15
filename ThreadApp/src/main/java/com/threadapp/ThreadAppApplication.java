package com.threadapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling 
public class ThreadAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThreadAppApplication.class, args);
	}

}
