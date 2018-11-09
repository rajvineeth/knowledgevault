package com.stackroute.knowledgevault.paragraphtokenizer.service;

import com.stackroute.knowledgevault.paragraphtokenizer.model.Document;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private Document document;

    @KafkaListener(topics="test", groupId ="group_id")
    public void consume(String message, ParaTokenizerImpl paraTokenizer){
        this.document = document;
        paraTokenizer.tokenizePara(document);
        System.out.println("consumed message:"+message);
    }
    @KafkaListener(topics="test",groupId = "group_json", containerFactory= "documentKafkaListenerFactory")
    public void consumejson(Document document, ParaTokenizerImpl paraTokenizer){
        this.document = document;
        paraTokenizer.tokenizePara(document);
        System.out.println("consumed message"+document.toString());
    }

}