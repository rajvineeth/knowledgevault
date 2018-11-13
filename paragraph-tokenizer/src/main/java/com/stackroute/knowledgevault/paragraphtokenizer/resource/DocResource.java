package com.stackroute.knowledgevault.paragraphtokenizer.resource;

import com.stackroute.knowledgevault.paragraphtokenizer.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Arrays;
import java.util.List;

public class DocResource {

    @Autowired
    private KafkaTemplate<String, List<Document>> kafkaTemplate;

    private static final String TOPIC = "paraTokens";

    public String post() {
        kafkaTemplate.send(TOPIC, Arrays.asList(new Document(1, "sdhuash"), new Document(1,"sdshd")));
        return "published successfully";
    }
}