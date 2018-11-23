package com.stackroute.knowledgevault.paragraphprocessor.communicators;

import com.stackroute.knowledgevault.domain.JSONld;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * This class contains logic to send data over kafka message bus
 */
@Component
public class KafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String,JSONld> kafkaTemplate ;

    private static final String TOPIC = "kafkaTest";

        public String post(JSONld data) {
        try{
            this.kafkaTemplate.send(TOPIC,data);
        }catch( Exception e) {
            LOGGER.info(String.valueOf(e.getStackTrace()));
        }
        return "message published  successfully";
    }
}
