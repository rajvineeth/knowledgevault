package com.stackroute.knowledgevault;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PopulatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(PopulatorApplication.class, args);
	}
}
