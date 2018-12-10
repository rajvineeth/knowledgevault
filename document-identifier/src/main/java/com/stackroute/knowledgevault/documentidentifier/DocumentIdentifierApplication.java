package com.stackroute.knowledgevault.documentidentifier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = "com.stackroute.agent,com.stackroute.knowledgevault.documentidentifier")
public class DocumentIdentifierApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocumentIdentifierApplication.class, args);
	}


}
