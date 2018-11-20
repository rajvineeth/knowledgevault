package com.stackroute.knowledgevault.paragraphtokenizer.resource;

import com.stackroute.knowledgevault.domain.Document;
import com.stackroute.knowledgevault.paragraphtokenizer.listener.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DocResource {

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
        return "published successfully";
    }
}