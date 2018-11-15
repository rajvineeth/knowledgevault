//package com.stackroute.knowledgevault.populator.service;
//
//import com.stackroute.knowledgevault.domain.JsonLDObject;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//
//@Service
//public class KafkaConsumer {
//    @KafkaListener(topics="document", groupId ="group_id")
//    public void consume(String message){
//        System.out.println("consumed message:"+message);
//    }
//    @KafkaListener(topics="document",groupId = "group_json", containerFactory= "userKafkaListenerFactory")
//    public void consumejson(JsonLDObject res){
//        System.out.println("consumed message"+res.toString());
//    }
//
//}
