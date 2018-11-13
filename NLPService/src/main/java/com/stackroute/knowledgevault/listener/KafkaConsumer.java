package com.stackroute.knowledgevault.listener;

import com.stackroute.knowledgevault.domain.ExtractedFileData;
import com.stackroute.knowledgevault.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class KafkaConsumer {

    private Logger logger;


    @Autowired
    private DocumentService documentService;

    @KafkaListener(topics="document",groupId = "group_json", containerFactory= "userKafkaListenerFactory")
    public void consumejson(List<ExtractedFileData> extractedFileData){
        documentService.saveDocuments(extractedFileData);
        System.out.println("done");
    }
}