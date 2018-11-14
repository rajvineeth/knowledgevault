package com.stackroute.populator.repository;

import com.stackroute.populator.domain.Anatomy;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface AnatomyRepo extends Neo4jRepository<Anatomy,Long> {
}
