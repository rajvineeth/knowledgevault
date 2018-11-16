package com.stackroute.knowledgevault.paragraphprocessor.communicators;

import com.stackroute.knowledgevault.domain.JSONld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String,JSONld> kafkaTemplate;

    private static final String TOPIC = "kafkaTest";

        public String post(JSONld data) {
        try{
            this.kafkaTemplate.send(TOPIC,data);
        }catch( Exception e) {
            e.printStackTrace();
        }
        return "Published successfully";
    }
}
