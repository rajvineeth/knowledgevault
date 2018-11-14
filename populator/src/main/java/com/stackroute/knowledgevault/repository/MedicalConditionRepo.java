package com.stackroute.knowledgevault.repository;

import com.stackroute.knowledgevault.domain.MedicalCondition;
import org.springframework.data.neo4j.repository.Neo4jRepository;


public interface MedicalConditionRepo extends Neo4jRepository<MedicalCondition,Long> {
}
