package com.stackroute.knowledgevault.extractorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ExtractorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExtractorServiceApplication.class, args);
	}
}