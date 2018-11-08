package com.stackroute.communicators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(Producer.class);

    @Value("${cloudkarafka.topic}")
    private String topic;

    Producer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(SampleMessage message) {
        this.kafkaTemplate.send(topic, message.getMessage());
        LOGGER.info("sent sample message: {} to {}",message,topic);
    }

}
