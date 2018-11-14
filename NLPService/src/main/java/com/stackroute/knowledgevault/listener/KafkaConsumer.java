package com.stackroute.knowledgevault.listener;

import com.stackroute.knowledgevault.domain.ExtractedFileData;
import com.stackroute.knowledgevault.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

/**
 * Class for Kafka consumer. consumes data from Extractor service and saves it local database (MongoDB)
 */

@Service
public class KafkaConsumer {

    private Logger logger;


    @Autowired
    private DocumentService documentService;

    /*
        The function consumejson receives a list of documents (Class: ExtractedFIleData) from extractor service and saves to
        MongoDb
    */

    @KafkaListener(topics="document",groupId = "group_json", containerFactory= "userKafkaListenerFactory")
    public void consumejson(List<ExtractedFileData> extractedFileData){
        documentService.saveDocuments(extractedFileData);
        logger.info("done");
    }
}