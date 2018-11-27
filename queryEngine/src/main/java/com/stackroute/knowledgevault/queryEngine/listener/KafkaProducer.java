package com.stackroute.knowledgevault.queryEngine.listener;

import com.stackroute.knowledgevault.domain.ProcessedInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

<<<<<<< HEAD
    @Autowired
    private KafkaTemplate<String, ProcessedInput> kafkaTemplate;

//    public KafkaProducer(KafkaTemplate<String, ProcessedInput> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }
=======
    private KafkaTemplate<String, ProcessedInput> kafkaTemplate;

    @Autowired
    public KafkaProducer(KafkaTemplate<String, ProcessedInput> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
>>>>>>> 95da466924c1afe1b7e35e49ffd24a2b8819830d

    private static final String TOPIC = "queryOutput";

    public String post(ProcessedInput queryOutput) {
        LOGGER.info("posting to kafka...");
        this.kafkaTemplate.send(TOPIC, queryOutput);
        return "published successfully";
    }
}