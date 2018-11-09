package com.stackroute.knowledgevault.paragraphtokenizer.resource;

import com.stackroute.knowledgevault.paragraphtokenizer.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

public class DocResource {

    @Autowired
    private KafkaTemplate<String, Document> kafkaTemplate;

    private static final String topic = "paraTokens";

    public String post() {
        kafkaTemplate.send(topic, new Document(2, "sdgasgdaisg"));
        return "published successfully";
    }
}
