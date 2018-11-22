package com.stackroute.knowledgevault.documentidentifier.controller;


import com.stackroute.knowledgevault.domain.JsonLDObject;
import com.stackroute.knowledgevault.documentidentifier.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class, to make GET request.
*/

@RestController
@RequestMapping("/api/v1/document")
public class DocumentController {

    @Qualifier("DocumentServiceImpl")
    private DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @Autowired
    private KafkaTemplate<String, JsonLDObject> kafkaTemplate;

    private static final String KafkaTopic ="prod2";

    /*
        Function to produce the processed documents to the Kafka topic "prod2" and as a get request.
        An object of DocumentService is called to process the docs present in the local database.
    */

    @GetMapping()
    public ResponseEntity<?> getAllTerms(){
        ResponseEntity responseEntity;
        List<JsonLDObject> outputForDocList = documentService.getJsonLD(documentService.processDoc(documentService.getAllDocuments(), 2));
        for(JsonLDObject object: outputForDocList){
            kafkaTemplate.send(KafkaTopic, object);
        }

        responseEntity = new ResponseEntity<List<JsonLDObject>>(outputForDocList, HttpStatus.OK);
        return responseEntity;
    }

}