package com.stackroute.knowledgevault.queryengine.service;

import com.stackroute.knowledgevault.domain.ExtractedFileData;
import com.stackroute.knowledgevault.queryengine.repository.ExtractedFileDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExtractedDataService {
    private ExtractedFileDataRepo extractedFileDataRepo;
    @Autowired
    public ExtractedDataService(ExtractedFileDataRepo extractedFileDataRepo) {
        this.extractedFileDataRepo = extractedFileDataRepo;
    }
    public Optional<ExtractedFileData> getDocById(int id)  {
        Optional<ExtractedFileData> doc;
        doc=extractedFileDataRepo.findById(id);
        return doc;
    }
}
