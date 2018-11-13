package com.stackroute.knowledgevault.paragraphtokenizer.resource;

import com.stackroute.knowledgevault.paragraphtokenizer.model.Document;
import com.stackroute.knowledgevault.paragraphtokenizer.service.ParaTokenizerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

//@RestController
//@RequestMapping("kafka")
public class DocResource {

    private KafkaTemplate<String, List<Document>> kafkaTemplate;

    public DocResource(KafkaTemplate kt) {
        this.kafkaTemplate = kt;
    }

    public void setKafkaTemplate(KafkaTemplate<String, List<Document>> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    private static final String TOPIC = "paraTokens";

    //    @GetMapping("/publish/{name}")
    public String post(List<Document> list) {
        this.kafkaTemplate.send(TOPIC,list);
        return "published successfully";
    }
}
