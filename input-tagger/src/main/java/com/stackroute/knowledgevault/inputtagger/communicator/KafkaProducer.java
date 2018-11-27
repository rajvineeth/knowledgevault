package com.stackroute.knowledgevault.inputtagger.communicator;

import com.stackroute.knowledgevault.domain.ProcessedInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    private KafkaTemplate<String, ProcessedInput> kafkaTemplate;

    @Autowired
    public KafkaProducer(KafkaTemplate<String, ProcessedInput> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    private static final String TOPIC = "queryInput";

    public String post(ProcessedInput userInput) {
        LOGGER.info("inside KafkaProducer.post()");
        LOGGER.info("posting to kafka...");
        this.kafkaTemplate.send(TOPIC, userInput);
        return "published successfully";
    }
}
