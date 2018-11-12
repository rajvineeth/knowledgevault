package com.stackroute.knowledgevault.paragraphtokenizer;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ParagraphTokenizerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParagraphTokenizerApplication.class, args);
	}

	@Bean
	public ApplicationRunner runner(final Producer producer) {
		return (args) -> {
			for (int i = 1; i < 2; i++) {
				producer.send(new KvMessage(i, "A simple test message"));
			}
		};
	}
}
