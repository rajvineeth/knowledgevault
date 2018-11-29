package com.stackroute.knowledgevault.paragraphprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ParagraphProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParagraphProcessorApplication.class, args);
	}

}
