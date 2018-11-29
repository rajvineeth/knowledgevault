package com.stackroute.knowledgevault.documentidentifier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DocumentIdentifierApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocumentIdentifierApplication.class, args);
	}


}
