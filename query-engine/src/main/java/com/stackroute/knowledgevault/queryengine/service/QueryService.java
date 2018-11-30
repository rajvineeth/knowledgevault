package com.stackroute.knowledgevault.queryengine.service;

import com.stackroute.knowledgevault.domain.OutputResult;
import org.neo4j.driver.v1.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class QueryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueryService.class);

    public OutputResult runquery(Driver driver, String k1, String k2) {
        LOGGER.info("received keywords from tagger");
        Map<String, String> map = new HashMap<>();
        OutputResult outputResult = new OutputResult();

        // Sessions are lightweight and disposable connection wrappers.
        try (Session session = driver.session()) {
            // Wrapping Cypher in an explicit transaction provides atomicity
            // and makes handling errors much easier.
            String q = "MATCH (A:" + k2 + "),p=(B)-[r]-(A) WHERE A.name contains '" + k1 + "' RETURN A.name As name1, labels(A) as name1label, TYPE(r) as ril, B.name As name2, labels(B) as name2label LIMIT 10";
            try (Transaction tx = session.beginTransaction()) {
                LOGGER.info(q);
                StatementResult result = tx.run(q);
                while (result.hasNext()) {
                    Record record = result.next();
                    // Values can be extracted from a record by index or name.
//                    String r = record.get("name1label").asString();
//
                    outputResult.Node1 = record.get("name1").asString();
                    outputResult.Node1label = record.get("name1label").asString();
                    outputResult.Relation = record.get("ril").asString();
                    outputResult.Node2 = record.get("name2").asString();
                    outputResult.Node2label = record.get("name2label").asString();


                }
                LOGGER.info("output :{}", map);
                LOGGER.info("Out of string loop");

                tx.success();  // Mark this write as successful.
                return outputResult;
            }
        }
    }

    public void loadgraph(Driver driver) {
        LOGGER.info("Loading The Graph");
        try (Session session = driver.session()) {
            String g1 = "LOAD CSV WITH HEADERS FROM 'https://gist.githubusercontent.com/anand-jadhav/323d279c7b3ef477c5555a031b00da73/raw/e366c239441db747d507bbc603bb010f5698978c/sym_t.csv' AS line " +
                    "CREATE (def:MedicalSymptom{syd:line.syd,name:line.symptom})";
            String g2 = "LOAD CSV WITH HEADERS FROM 'https://gist.githubusercontent.com/anand-jadhav/f20082c9fd9db159cbd7101713ebfd9e/raw/721f1ccebac5fabfdec023aab3b489e1b9f0af96/dianew.csv' AS line " +
                    "CREATE (def:MedicalCondition{did:line.did,name:line.diagnose})";
            String g3 = "MATCH(a:MedicalCondition),(b:MedicalSymptom),(n:Matchers) " +
                    "WHERE n.did=a.did AND n.syd=b.syd " +
                    "CREATE (a)-[r:has_symptom]->(b)";
            try (Transaction tx = session.beginTransaction()) {
                tx.run(g1);
                tx.run(g2);
                tx.run(g3);
                tx.success();  // Mark this write as successful.
                LOGGER.info("Graph Loaded Succesfully");

            }
        }
    }

    public void close(Driver driver) {
        // Closing a driver immediately shuts down all open connections.
        LOGGER.info("Closing Driver Session");
        driver.close();
    }
}
