package com.stackroute.repository;


import com.stackroute.domain.ExtractedFileData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends MongoRepository<ExtractedFileData, Integer> {
}
