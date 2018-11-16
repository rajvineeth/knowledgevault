package com.stackroute.knowledgevault.paragraphprocessor.communicators;

import com.stackroute.knowledgevault.domain.JSONld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaProducer {
    private KafkaTemplate<String,JSONld> kafkaTemplate;

    public KafkaProducer(){}
    public KafkaProducer(KafkaTemplate<String,JSONld> template)
    {
        this.kafkaTemplate = template;
    }

    private static final String TOPIC = "kafkaTest";

    public String post(JSONld data) {
        this.kafkaTemplate.send(TOPIC,data);
        return "Published successfully";
    }
}
