package com.stackroute.knowledgevault.listener;

import com.stackroute.knowledgevault.domain.ExtractedFileData;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics="document", groupId ="group_id")
    public void consume(String message){
        System.out.println("consumed message:"+message);
    }
    @KafkaListener(topics="document",groupId = "group_json", containerFactory= "documentKafkaListenerFactory")
    public void consumejson(ExtractedFileData extractedFileData){
        System.out.println("consumed message"+ extractedFileData.toString());
    }
}
