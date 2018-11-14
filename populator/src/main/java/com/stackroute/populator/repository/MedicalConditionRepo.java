package com.stackroute.populator.repository;

import com.stackroute.populator.domain.MedicalCondition;
import org.springframework.data.neo4j.repository.Neo4jRepository;


public interface MedicalConditionRepo extends Neo4jRepository<MedicalCondition,Long> {
}
