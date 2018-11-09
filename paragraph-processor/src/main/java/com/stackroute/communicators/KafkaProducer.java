package com.stackroute.communicators;

import org.springframework.kafka.core.KafkaTemplate;


public class KafkaProducer {

    private KafkaTemplate<String,String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String,String> template) {
        this.kafkaTemplate = template;
    }

    private static final String TOPIC = "kafkaTest";

    public String post(String resp) {
        kafkaTemplate.send(TOPIC,resp);
        return "Published successfully";
    }
}
