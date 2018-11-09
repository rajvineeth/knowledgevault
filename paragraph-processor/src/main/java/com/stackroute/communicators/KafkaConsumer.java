package com.stackroute.communicators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    // this will store the consumed data
    String input;

    /**
     * This method consumes the data for which it is listening kafka cluster to
     * @param message: the wanted message
     */
    @KafkaListener(topics = "kafkaTest", groupId = "group_id")
    public void consume(String message) {
        this.input=message;
        LOGGER.info("Consumed message: {} ",message);
        LOGGER.info("response message: Thanks...I got the data!!");
    }

}
