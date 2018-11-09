//package com.stackroute.service;
//
//import com.stackroute.domain.DocResult;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//
//import java.util.Collection;
//import java.util.Map;
//
//@Service
//public class KafkaConsumer {
//    @KafkaListener(topics="kafka_example", groupId ="group_id")
//    public void consume(String message){
//        System.out.println("consumed message:"+message);
//    }
//    @KafkaListener(topics="kafka_example_jsonh",groupId = "group_json", containerFactory= "userKafkaListenerFactory")
//    public void consumejson(Collection<DocResult> res){
//        System.out.println("consumed message"+res.toString());
//    }
//
//}
