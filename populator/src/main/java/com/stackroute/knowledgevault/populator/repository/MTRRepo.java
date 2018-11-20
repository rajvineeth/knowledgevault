package com.stackroute.knowledgevault.populator.repository;

import com.stackroute.knowledgevault.domain.MTR;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MTRRepo extends Neo4jRepository<MTR,String> {
    @Query("MATCH (MedicalSymptom:MT { type: {node1} })-[r1]-(relations:MTR)-[r2]-(MedicalCondition:MT { type: {node2} }) return relations")
    public List<MTR> getRelations(@Param("node1") String node1, @Param("node2") String node2);
}
