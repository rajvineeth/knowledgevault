package com.stackroute.populator.repository;

import com.stackroute.populator.domain.Treatment;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface TreatmentRepo extends Neo4jRepository<Treatment,Long> {
}
