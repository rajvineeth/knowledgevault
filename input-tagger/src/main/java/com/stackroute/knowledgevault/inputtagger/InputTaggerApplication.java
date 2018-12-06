package com.stackroute.knowledgevault.inputtagger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class InputTaggerApplication {

	public static void main(String[] args) {
		SpringApplication.run(InputTaggerApplication.class, args);
	}
}
