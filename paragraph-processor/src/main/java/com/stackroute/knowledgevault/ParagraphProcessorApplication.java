package com.stackroute.knowledgevault;

import com.stackroute.knowledgevault.domain.JSONld;
import com.stackroute.knowledgevault.paragraphprocessor.Processor;
import com.stackroute.knowledgevault.paragraphprocessor.communicators.KafkaConsumer;
import com.stackroute.knowledgevault.paragraphprocessor.communicators.KafkaProducer;
import com.stackroute.knowledgevault.paragraphprocessor.utilities.FillUpData;
import org.json.JSONObject;
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
	private KafkaTemplate<String,JSONld> kafkaTemplate;

	private static KafkaProducer producer;

	private static final Logger LOGGER = LoggerFactory.getLogger(ParagraphProcessorApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ParagraphProcessorApplication.class, args);

//		Processor processor = new Processor();
//		FillUpData fillUpData = new FillUpData();
//		String paragraph = "My name is neeraj. I am suffering from cancer.I have cancer in my lungs.";
//		paragraph.toLowerCase();
//		JSONObject data = fillUpData.fill(processor.paraProcessing(paragraph));
//		LOGGER.info("producer message: {} ",producer.post(new JSONld(data)));
//		LOGGER.info("producer message: {} \n ","hey...i have sent the message");

	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		producer = new KafkaProducer(this.kafkaTemplate);
	}
}
