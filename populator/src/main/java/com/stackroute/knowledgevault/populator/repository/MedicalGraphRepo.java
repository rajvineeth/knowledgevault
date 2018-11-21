package com.stackroute.knowledgevault.populator.repository;

import com.stackroute.knowledgevault.domain.*;
import com.stackroute.knowledgevault.populator.service.MedicalConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

public interface MedicalGraphRepo extends Neo4jRepository<MedicalGraph,String> {
//    @Query("MERGE (N:MedicalCondition { type: {name} })")
//    public MedicalGraph createMedicalCondition(@Param("name") MedicalCondition medicalCondition);
//    @Query("MERGE (N:Anatomy { type: {name} })")
//    public MedicalGraph createAnatomy(@Param("name") Anatomy anatomy);
//    @Query("MERGE (N:MedicalSymptom { type: {name} })")
//    public MedicalGraph createSymptom(@Param("name") MedicalSymptom symptom);
    @Query("MATCH (u:MedicalCondition {type:{node1}}), (r:MedicalSymptom {type:{node2}})\n" +
            "CREATE (u)-[:{rel}]->(r)")
    public void createR(@Param("node1") String node1,@Param("rel") String rel,@Param("node2") String node2);
    @Query("{q}")
    public void createka(@Param("q") String q);

}
