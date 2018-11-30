package com.stackroute.knowledgevault.queryengine.repository;

import com.stackroute.knowledgevault.domain.ExtractedFileData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtractedFileDataRepo extends MongoRepository<ExtractedFileData,Integer> {
}
