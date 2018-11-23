package com.stackroute.knowledgevault.paragraphtokenizer.resource;

import com.stackroute.knowledgevault.domain.Document;
import com.stackroute.knowledgevault.paragraphtokenizer.listener.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * the kafka producer function that sends data to the kafka server.
 */

@Component
public class DocResource {

    @Value("${production_message}")
    private String message;

    private KafkaTemplate<String, Document> kafkaTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(DocResource.class);

    public DocResource(KafkaTemplate<String, Document> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    private static final String TOPIC = "para-tokens";

    public String post(List<Document> list) {
        for (Document doc : list){
            LOGGER.info(doc.toString());
            this.kafkaTemplate.send(TOPIC, doc);
        }
        return message;
    }
}