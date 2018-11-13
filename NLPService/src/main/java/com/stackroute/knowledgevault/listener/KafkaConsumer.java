package com.stackroute.knowledgevault.listener;

<<<<<<< HEAD:NLPService/src/main/java/com/stackroute/knowledgevault/listener/KafkaConsumer.java
import com.stackroute.knowledgevault.domain.ExtractedFileData;
import com.stackroute.knowledgevault.services.DocumentService;
=======
import com.stackroute.domain.ExtractedFileData;
import com.stackroute.services.DocumentService;
>>>>>>> bb029cc86a748b489a73e19bef4515706a37f1cc:NLPService/src/main/java/com/stackroute/listener/KafkaConsumer.java
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
        logger.info("done");
    }
}