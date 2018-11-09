package com.stackroute.listener;

import com.stackroute.domain.DocumentReader;
//import com.stackroute.kafka.springbootkafkaconsumerexample.model.User;
import com.stackroute.domain.OutputForDoc;
import com.stackroute.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.awt.event.ContainerListener;
import java.util.List;

@Service
public class KafkaConsumer {

    @Autowired
    private DocumentService documentService;

    @KafkaListener(topics="kafka_example6",groupId = "group_json", containerFactory= "userKafkaListenerFactory")
    public void consumejson(List<DocumentReader> documentReader){
        System.out.println("consumed message"+documentReader.get(0).toString());
        List<DocumentReader> documentReaders = documentService.saveDocuments(documentReader);
        System.out.println(documentReaders);
        //System.out.println(documentReaders);
    }
}