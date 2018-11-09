package com.stackroute.knowledgevault.paragraphtokenizer.service;

import com.stackroute.knowledgevault.paragraphtokenizer.model.Document;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private Document document;

    @KafkaListener(topics="kafka_example", groupId ="group_id")
    public void consume(String message){
        System.out.println("consumed message:"+message);
    }
    @KafkaListener(topics="kafka_example_jsonh",groupId = "group_json", containerFactory= "userKafkaListenerFactory")
    public void consumejson(Document document, ParaTokenizerImpl paraTokenizer){
        this.document = document;
        paraTokenizer.tokenizePara(document);
        System.out.println("consumed message"+document.toString());
    }

}