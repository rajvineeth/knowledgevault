package com.stackroute.knowledgevault.inputkafka.producer;

import com.stackroute.knowledgevault.domain.UserInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);
    private static final String TOPIC_POS = "user-input-POS";
    private static final String TOPIC_LEMMA = "user-input-lemma";

    private KafkaTemplate<String, UserInput> kafkaTemplate;

    @Autowired
    public KafkaProducer(KafkaTemplate<String, UserInput> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public String post(UserInput userInput) {
        LOGGER.info("posting to kafka...");
        this.kafkaTemplate.send(TOPIC_LEMMA, userInput);

        this.kafkaTemplate.send(TOPIC_POS, userInput);
        return "published successfully";
    }
}
