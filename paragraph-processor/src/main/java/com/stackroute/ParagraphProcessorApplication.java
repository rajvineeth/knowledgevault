package com.stackroute;

import com.stackroute.communicators.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.kafka.core.KafkaTemplate;


@SpringBootApplication
public class ParagraphProcessorApplication implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private KafkaTemplate<String,String> kafkaTemplate;

	private static KafkaProducer producer;
	private static final Logger LOGGER = LoggerFactory.getLogger(ParagraphProcessorApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ParagraphProcessorApplication.class, args);
		Processor pst = new Processor();
		pst.getFullTextSearch().indexer();

		String[] keys = {"fibrohistiocytic","dermoid","nervous","pulmonary"};
		for(String word: keys){
			String response = pst.getFullTextSearch().search(word);
			LOGGER.info("data generated so far:\n {}",response);
			LOGGER.info("producer message: {} ",producer.post(response));
			LOGGER.info("producer message: {} \n ","hey...i have sent the message");
		}

	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		producer = new KafkaProducer(this.kafkaTemplate);
	}
}
