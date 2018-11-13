package com.stackroute.listener;


import com.stackroute.domain.ExtractedFileData;
import com.stackroute.domain.OutputForDoc;
import com.stackroute.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class KafkaProducer {

    private Logger logger;

    @Autowired
    private KafkaTemplate<String, List<OutputForDoc>> kafkaTemplate;

    @Autowired
    private DocumentService documentService;

    private static final String Topic="kafka_example";

    public void produceJson(){
        List<ExtractedFileData> extractedFileDataList = documentService.getAllDocuments();
        List<OutputForDoc> outputForDocList = documentService.processDoc(extractedFileDataList);
        logger.info(outputForDocList.get(0).toString());
        kafkaTemplate.send(Topic, outputForDocList);
    }

}
