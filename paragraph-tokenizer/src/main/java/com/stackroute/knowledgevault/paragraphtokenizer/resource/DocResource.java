package com.stackroute.knowledgevault.paragraphtokenizer.resource;

import com.stackroute.knowledgevault.domain.Document;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DocResource {

    private KafkaTemplate<String, Document> kafkaTemplate;

    public DocResource(KafkaTemplate<String, Document> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    private static final String TOPIC = "cgi4";

    public String post(List<Document> list) {
        for (Document doc : list){
            System.out.println(doc.toString());
            this.kafkaTemplate.send(TOPIC, doc);
        }
        return "published successfully";
    }
}