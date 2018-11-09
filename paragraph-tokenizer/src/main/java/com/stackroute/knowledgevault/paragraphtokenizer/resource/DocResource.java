package com.stackroute.knowledgevault.paragraphtokenizer.resource;

import com.stackroute.knowledgevault.paragraphtokenizer.model.Document;
import com.stackroute.knowledgevault.paragraphtokenizer.service.ParaTokenizerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("kafka")
public class DocResource {

    @Autowired
    private KafkaTemplate<String, List<Document>> kafkaTemplate;

    @Autowired
    private ParaTokenizerImpl paraTokenizer;

    private static final String topic = "paraTokens";

    @GetMapping("/publish/{name}")
    public String post() {
        kafkaTemplate.send(topic, paraTokenizer.tokenizePara(document));
        return "published successfully";
    }
}
