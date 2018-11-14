package com.stackroute.inputprocessor.listener;

import com.stackroute.domain.Input;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics="kafka_example", groupId ="group_id")
    public void consume(String message){
        System.out.println("consumed message:"+message);
    }
    @KafkaListener(topics="kafka_example_jsonh",groupId = "group_json", containerFactory= "documentKafkaListenerFactory")
    public void consumejson(Input DOCUMENT){
        System.out.println("consumed message"+DOCUMENT.toString());
    }
}
