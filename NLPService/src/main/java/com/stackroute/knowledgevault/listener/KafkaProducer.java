//package com.stackroute.listener;
//
//
//import ExtractedFileData;
//import OutputForDoc;
//import DocumentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.logging.Logger;
//
//@Service
//public class KafkaProducer {
//
//    private Logger logger;
//
//    @Autowired
//    private KafkaTemplate<String, List<OutputForDoc>> kafkaTemplate;
//
//    @Autowired
//    private DocumentService documentService;
//
//    private static final String Topic="prod2";
//
//    public void produceJson(){
//        List<OutputForDoc> outputForDocList = documentService.processDoc(documentService.getAllDocuments());
//        kafkaTemplate.send(Topic, outputForDocList);
//
//    }
//
//}
