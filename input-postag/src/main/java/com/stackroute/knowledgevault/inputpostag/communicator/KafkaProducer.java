package com.stackroute.knowledgevault.inputpostag.communicator;

import com.stackroute.knowledgevault.domain.InputPOS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    private KafkaTemplate<String, InputPOS> kafkaTemplate;

    @Autowired
    public KafkaProducer(KafkaTemplate<String, InputPOS> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    private static final String TOPIC = "input-POS";

    public String post(InputPOS userInput) {
        LOGGER.info("posting to kafka...");
        this.kafkaTemplate.send(TOPIC, userInput);
        return "published successfully";
    }
}
