package com.stackroute.knowledgevault.populator.service;

import com.stackroute.knowledgevault.domain.Document;
import com.stackroute.knowledgevault.populator.repository.DocumentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {
    private DocumentRepo documentRepo;
    @Autowired
    public DocumentService(DocumentRepo documentRepo){
        this.documentRepo=documentRepo;
    }

    public Document saveDocument(Document document) {
        return documentRepo.save(document);
    }
    public List<Document> documentList() {
        List<Document> listcondtions = (List)documentRepo.findAll();
        return listcondtions;
    }
}
