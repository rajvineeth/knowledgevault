package com.stackroute.knowledgevault.paragraphtokenizer.resource;

import com.stackroute.domain.Document;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.ArrayList;
import java.util.List;

public class DocResource {

    private KafkaTemplate<String, Document> kafkaTemplate;

    public DocResource(KafkaTemplate<String, Document> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    private static final String TOPIC = "cgi1";

    public String post() {
        List<Document> list = new ArrayList();
        list.add(new Document(1,"asdiyg"));
        for (Document doc : list){
            this.kafkaTemplate.send(TOPIC, doc);
        }
        return "published successfully";
    }
}