package com.stackroute.knowledgevault.paragraphtokenizer.resource;

import com.stackroute.knowledgevault.paragraphtokenizer.model.Document;
import com.stackroute.knowledgevault.paragraphtokenizer.service.ParaTokenizerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@RestController
//@RequestMapping("kafka")
public class DocResource {

    @Autowired
    private KafkaTemplate<String, List<Document>> kafkaTemplate;

    private static final String topic = "paraTokens";

//    @GetMapping("/publish/{name}")
    public String post(ParaTokenizerImpl paraTokenizer) {
        kafkaTemplate.send(topic, paraTokenizer.getList());
        return "published successfully";
    }
}
