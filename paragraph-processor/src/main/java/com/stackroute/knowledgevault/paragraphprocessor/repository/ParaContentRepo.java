package com.stackroute.knowledgevault.paragraphprocessor.repository;

import com.stackroute.knowledgevault.domain.ParaContent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParaContentRepo extends MongoRepository<ParaContent,Integer> {
}
