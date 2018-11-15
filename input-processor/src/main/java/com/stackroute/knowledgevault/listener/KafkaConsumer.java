package com.stackroute.knowledgevault.listener;

import com.stackroute.knowledgevault.domain.Input;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics="kafka_example", groupId ="group_id")
    public void consume(String message){
        System.out.println("consumed message:"+message);
    }
    @KafkaListener(topics="kafka_example_jsonh",groupId = "group_json", containerFactory= "documentKafkaListenerFactory")
    public void consumejson(Input INPUT){
        System.out.println("consumed message"+INPUT.toString());
    }
}
