package com.stackroute.controller;


import com.stackroute.domain.DocumentReader;
import com.stackroute.domain.OutputForDoc;
import com.stackroute.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/document")
public class DocumentController {
    @Qualifier("DocumentServiceImpl")
    private DocumentService documentService;

    @Autowired
    public DocumentController(DocumentService documentService){
        this.documentService = documentService;
    }

    @PostMapping()
    public ResponseEntity<?> saveDocuments(@RequestBody List<DocumentReader> documentReaderList){
        ResponseEntity responseEntity;
        List<DocumentReader> documentReaders = documentService.saveDocuments(documentReaderList);
        responseEntity = new ResponseEntity<List<DocumentReader>>(documentReaders, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping()
    public ResponseEntity<?> getAllTerms(){
        ResponseEntity responseEntity;
        List<DocumentReader> documentReaderList = documentService.getAllDocuments();
        List<OutputForDoc> outputForDocList = documentService.processDoc(documentReaderList);
        responseEntity = new ResponseEntity<List<OutputForDoc>>(outputForDocList, HttpStatus.OK);
        return responseEntity;
    }
}
