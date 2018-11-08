package com.stackroute;

import com.stackroute.algos.FullTextSearch;
import com.stackroute.algos.POSTagging;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ParagraphProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParagraphProcessorApplication.class, args);
	}
}
