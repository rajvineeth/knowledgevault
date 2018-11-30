package com.stackroute.knowledgevault.queryengine.repository;

import com.stackroute.knowledgevault.domain.ParaContent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ParaContentRepo extends MongoRepository<ParaContent,Integer> {
}

