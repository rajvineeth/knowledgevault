package com.stackroute.knowledgevault.inputpostag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class InputPostagApplication {

	public static void main(String[] args) {
		SpringApplication.run(InputPostagApplication.class, args);
	}
}
