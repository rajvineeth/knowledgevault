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


    public void close(Driver driver) {
        // Closing a driver immediately shuts down all open connections.
        LOGGER.info("Closing Driver Session");
        driver.close();
    }
}
