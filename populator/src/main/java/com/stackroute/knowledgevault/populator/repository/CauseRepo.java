package com.stackroute.knowledgevault.populator.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import com.stackroute.knowledgevault.domain.Cause;


public interface CauseRepo extends Neo4jRepository<Cause,Long> {
}
