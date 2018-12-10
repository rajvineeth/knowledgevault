package com.stackroute.knowledgevault.paragraphtokenizer.listener;

import com.stackroute.knowledgevault.domain.Document;
import com.stackroute.knowledgevault.domain.ExtractedFileData;
import com.stackroute.knowledgevault.paragraphtokenizer.resource.DocResource;
import com.stackroute.knowledgevault.paragraphtokenizer.service.ParaTokenizerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Kafka consumer service with listener that invokes tokenize() method,
 * whenever new data is available in the kafka server.
 */

@Service
public class KafkaConsumer {

    @Autowired
    private ParaTokenizerImpl paraTokenizer;

    @Autowired
    private DocResource docResource;

    @Value("${consumed.list}")
    private String listMessage;

    @Value("${consumed.message}")
    private String message;

    //to log data on the console
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);
    private List list;

    /*
     * This method consumes data from kafka server and makes call to kafka producer.
     */
    @KafkaListener(topics="extracted2",groupId = "group_json", containerFactory= "documentKafkaListenerFactory")
    public void consumejson(ExtractedFileData extractedFileData){
       Document document = new Document(extractedFileData.getId(), extractedFileData.getContent());
        list = paraTokenizer.tokenizePara(document);
        LOGGER.info(listMessage, list.toString());
        LOGGER.info(message);
        docResource.post(list);
    }
}
