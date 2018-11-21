package com.stackroute.knowledgevault.populator.repository;

import com.stackroute.knowledgevault.domain.MedicalSymptom;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface SymptomRepo extends Neo4jRepository<MedicalSymptom,String> {
}
