package com.stackroute.paragraphsummarizationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ParagraphSummarizationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParagraphSummarizationServiceApplication.class, args);
	}

}
