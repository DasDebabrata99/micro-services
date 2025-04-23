package com.example.EurekaClientdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EurekaClientdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientdemoApplication.class, args);
	}

}
