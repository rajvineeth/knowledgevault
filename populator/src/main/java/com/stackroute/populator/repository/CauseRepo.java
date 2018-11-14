package com.stackroute.populator.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import com.stackroute.populator.domain.Cause;


public interface CauseRepo extends Neo4jRepository<Cause,Long> {
}
