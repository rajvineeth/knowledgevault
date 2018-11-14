package com.stackroute.knowledgevault.paragraphtokenizer.listener;

import com.stackroute.domain.Document;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

//    @KafkaListener(topics="document", groupId ="group_json")
//    public void consume(String message){
//        System.out.println("consumed message:"+message);
//    }
    @KafkaListener(topics="document",groupId = "group_json", containerFactory= "documentKafkaListenerFactory")
    public void consumejson(Document DOCUMENT){
        System.out.println("consumed message"+DOCUMENT.toString());
    }
}
