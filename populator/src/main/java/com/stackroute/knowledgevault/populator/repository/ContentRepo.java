package com.stackroute.knowledgevault.populator.repository;

import com.stackroute.knowledgevault.domain.Content;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.ArrayList;

public interface ContentRepo extends Neo4jRepository<Content,String> {
}
