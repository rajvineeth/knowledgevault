package com.stackroute.paragraphtokenizer;

import com.stackroute.domain.ExtraxtedFileData;
import com.stackroute.paragraphtokenizer.resource.DocResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class ParagraphTokenizerApplication implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private KafkaTemplate<String, ExtraxtedFileData> kafkaTemplate;

	private static DocResource producer;

	public static void main(String[] args) {
		SpringApplication.run(ParagraphTokenizerApplication.class, args);
		producer.post();
		System.out.println("im here");
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		producer = new DocResource(this.kafkaTemplate);
	}
}
