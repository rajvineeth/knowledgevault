package com.stackroute.knowledgevault.knowledgevault.paragraphtokenizer.listener;

import com.stackroute.knowledgevault.knowledgevault.paragraphtokenizer.model.Document;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics="kafka_example", groupId ="group_id")
    public void consume(String message){
        System.out.println("consumed message:"+message);
    }
    @KafkaListener(topics="kafka_example_jsonh",groupId = "group_json", containerFactory= "documentKafkaListenerFactory")
    public void consumejson(Document DOCUMENT){
        System.out.println("consumed message"+DOCUMENT.toString());
    }
}
