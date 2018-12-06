package com.stackroute.knowledgevault.inputkafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class InputKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(InputKafkaApplication.class, args);
	}
}
