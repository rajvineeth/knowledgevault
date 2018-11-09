package com.stackroute.knowledgevault.paragraphtokenizer.kafka;

import com.stackroute.knowledgevault.paragraphtokenizer.model.KvMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${cloudkarafka.topic}")
    private String topic;

    public Producer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(KvMessage kvMessage) {
        this.kafkaTemplate.send(topic, kvMessage.getMessage());
        System.out.println("Sent kvMessage [" + kvMessage + "] to " + topic);
    }
}
