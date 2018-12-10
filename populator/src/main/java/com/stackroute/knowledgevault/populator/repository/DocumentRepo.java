package com.stackroute.knowledgevault.populator.repository;

import com.stackroute.knowledgevault.domain.Document;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.ArrayList;

public interface DocumentRepo extends Neo4jRepository<Document, String> {
}
