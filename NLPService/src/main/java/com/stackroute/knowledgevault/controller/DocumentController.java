package com.stackroute.knowledgevault.controller;


<<<<<<< HEAD:NLPService/src/main/java/com/stackroute/knowledgevault/controller/DocumentController.java
import com.stackroute.knowledgevault.domain.OutputForDoc;
import com.stackroute.knowledgevault.services.DocumentService;
=======
import com.stackroute.domain.OutputForDoc;
import com.stackroute.services.DocumentService;
>>>>>>> bb029cc86a748b489a73e19bef4515706a37f1cc:NLPService/src/main/java/com/stackroute/controller/DocumentController.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    private KafkaTemplate<String, List<OutputForDoc>> kafkaTemplate;

    private static final String KafkaTopic ="prod2";

    @GetMapping()
    public ResponseEntity<?> getAllTerms(){
        ResponseEntity responseEntity;
        List<OutputForDoc> outputForDocList = documentService.processDoc(documentService.getAllDocuments());
        kafkaTemplate.send(KafkaTopic, outputForDocList);
        responseEntity = new ResponseEntity<List<OutputForDoc>>(outputForDocList, HttpStatus.OK);
        return responseEntity;
    }

}