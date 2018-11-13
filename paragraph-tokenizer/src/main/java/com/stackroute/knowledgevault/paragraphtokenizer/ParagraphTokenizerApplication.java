package com.stackroute.knowledgevault.paragraphtokenizer;

import com.stackroute.knowledgevault.paragraphtokenizer.model.Document;
import com.stackroute.knowledgevault.paragraphtokenizer.resource.DocResource;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ParagraphTokenizerApplication implements ApplicationListener<ApplicationContextEvent> {

	@Autowired
	private KafkaTemplate<String, List<Document>> kafkaTemplate;

	private static DocResource producer;

	public static void main(String[] args) {
		SpringApplication.run(ParagraphTokenizerApplication.class, args);
		List<Document> list = new ArrayList() {
			{
				add(new Document(1,"sdsfsd"));
				add(new Document(2,"sfsd"));
				add(new Document(3,"sdsd"));

			}
		};

		System.out.println(producer.post(list));
	}

	@Override
	public void onApplicationEvent(ApplicationContextEvent applicationContextEvent) {
		producer = new DocResource(this.kafkaTemplate);
	}
}
