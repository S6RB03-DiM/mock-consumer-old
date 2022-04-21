package com.dinnerinmotion.mockservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MockServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(MockServiceApplication.class, args);
	}
}
