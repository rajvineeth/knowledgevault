package com.stackroute.knowledgevault.inputlemmatize.communicator;

import com.stackroute.knowledgevault.domain.InputLemma;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    private KafkaTemplate<String, InputLemma> kafkaTemplate;

    @Autowired
    public KafkaProducer(KafkaTemplate<String, InputLemma> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    private static final String TOPIC = "input-lemma";

    public String post(InputLemma inputLemma) {
        LOGGER.info("posting to kafka...");
        this.kafkaTemplate.send(TOPIC, inputLemma);
        return "published successfully";
    }
}
