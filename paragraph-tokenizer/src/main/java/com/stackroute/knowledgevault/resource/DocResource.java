package com.stackroute.knowledgevault.resource;

import com.stackroute.knowledgevault.domain.ExtraxtedFileData;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.ArrayList;
import java.util.List;

public class DocResource {

    private KafkaTemplate<String, ExtraxtedFileData> kafkaTemplate;

    public DocResource(KafkaTemplate<String, ExtraxtedFileData> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    private static final String TOPIC = "cgi1";

    public String post() {
        List<ExtraxtedFileData> list = new ArrayList();
        list.add(new ExtraxtedFileData(1,"asdiyg"));
        for (ExtraxtedFileData doc : list){
            this.kafkaTemplate.send(TOPIC, doc);
        }
        return "published successfully";
    }
}