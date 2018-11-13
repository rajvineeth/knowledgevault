package com.stackroute.knowledgevault.service;

import com.stackroute.knowledgevault.dao.DocumentDao;
import com.stackroute.knowledgevault.domain.ExtractedFileData;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    private DocumentDao documentDao;
    public KafkaConsumer(DocumentDao documentDao1){this.documentDao = documentDao1;}
    @KafkaListener(topics="document", groupId ="group_id")
    public void consume(String message){
        System.out.println("consumed message:"+message);
    }
    @KafkaListener(topics="document",groupId = "group_json", containerFactory= "userKafkaListenerFactory")
    public void consumejson(ExtractedFileData res){
        System.out.println("consumed message"+res.toString());
        documentDao.insertDoc(res);
    }

}
