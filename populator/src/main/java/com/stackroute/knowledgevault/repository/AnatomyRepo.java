package com.stackroute.knowledgevault.repository;

import com.stackroute.knowledgevault.domain.Anatomy;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface AnatomyRepo extends Neo4jRepository<Anatomy,Long> {
}
