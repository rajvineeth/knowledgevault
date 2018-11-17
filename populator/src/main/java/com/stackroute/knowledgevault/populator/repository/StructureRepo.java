//package com.stackroute.knowledgevault.populator.repository;
//
//import com.stackroute.knowledgevault.domain.Structure;
//import org.springframework.data.neo4j.annotation.Query;
//import org.springframework.data.neo4j.repository.Neo4jRepository;
//import org.springframework.data.repository.query.Param;
//
//public interface StructureRepo extends Neo4jRepository<Structure,Long> {
//    @Query("MERGE ({name}:MT { type: '{name}' }) return {name}")
//    public void createMT(@Param("name") String name);
//    @Query("MERGE ({name}:MTR { type: '{name}' }) return {name}")
//    public void createMTR(@Param("name") String name);
//    @Query("MERGE ({name}:MTR { type: '{name}' }) return {name}")
//    public void createR(@Param("name") String name);
//}
