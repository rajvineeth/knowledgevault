package com.stackroute.listener;


import com.stackroute.domain.DocumentReader;
import com.stackroute.domain.OutputForDoc;
import com.stackroute.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, List<OutputForDoc>> kafkaTemplate;

    @Autowired
    private DocumentService documentService;

    private static final String Topic="kafka_example";

    public void produceJson(){
        List<DocumentReader> documentReaderList = documentService.getAllDocuments();
        List<OutputForDoc> outputForDocList = documentService.processDoc(documentReaderList);
        kafkaTemplate.send(Topic, outputForDocList);
    }

}
