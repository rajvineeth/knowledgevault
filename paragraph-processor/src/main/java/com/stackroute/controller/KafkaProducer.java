package com.stackroute.controller;

import org.springframework.kafka.core.KafkaTemplate;


public class KafkaProducer {

    public String dummy = " dummy ";

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
