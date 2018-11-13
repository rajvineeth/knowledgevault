package com.stackroute.knowledgevault.repository;


import com.stackroute.knowledgevault.domain.ExtractedFileData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends MongoRepository<ExtractedFileData, Integer> {
}
