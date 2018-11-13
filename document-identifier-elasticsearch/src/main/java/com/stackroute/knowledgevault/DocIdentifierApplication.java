package com.stackroute.knowledgevault;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class DocIdentifierApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocIdentifierApplication.class, args);
	}
}
