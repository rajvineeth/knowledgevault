package com.stackroute.knowledgevault;

import com.stackroute.knowledgevault.paragraphprocessor.Processor;
import com.stackroute.knowledgevault.paragraphprocessor.communicators.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.List;

@SpringBootApplication
public class ParagraphProcessorApplication implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private KafkaTemplate<String,String> kafkaTemplate;

	private static KafkaProducer producer;

	private static final Logger LOGGER = LoggerFactory.getLogger(ParagraphProcessorApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ParagraphProcessorApplication.class, args);

		Processor pst = new Processor();
		pst.getFullTextSearch().setFilesPath("paragraph-processor/src/main/java/com/stackroute/knowledgevault/paragraphprocessor/assets/medicalRepositories");
		pst.getFullTextSearch().setIndexPath("paragraph-processor/src/main/java/com/stackroute/knowledgevault/paragraphprocessor/assets/repoIndices");
		pst.getFullTextSearch().indexer();

		String[] keys = {"ughh","fox","brown"};//"narcolepsy","dermoid","nervous","quick"};
		for(String word: keys){
			List<String> response = pst.getFullTextSearch().search(word);
			LOGGER.info("data generated so far: {}",response);
//			LOGGER.info("producer message: {} ",producer.post(response));
//			LOGGER.info("producer message: {} \n ","hey...i have sent the message");
		}
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		producer = new KafkaProducer(this.kafkaTemplate);
	}
}
