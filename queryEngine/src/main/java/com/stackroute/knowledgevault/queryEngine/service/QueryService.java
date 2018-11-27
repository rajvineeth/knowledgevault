package com.stackroute.knowledgevault.queryEngine.service;

import com.stackroute.knowledgevault.domain.ProcessedInput;
import com.stackroute.knowledgevault.queryEngine.listener.KafkaProducer;
import org.neo4j.driver.v1.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class QueryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryService.class);

    //    private DriverInit driver = new DriverInit("bolt://localhost:7687", "neo4j", "123456");
//    Driver drive = driver.getDriver();
    public ProcessedInput runquery(Driver driver, String k1, String k2) {
        LOGGER.info("received keywords from tagger");
        Map<String, String> map = new HashMap<>();
        // Sessions are lightweight and disposable connection wrappers.
        try (Session session = driver.session()) {
            // Wrapping Cypher in an explicit transaction provides atomicity
            // and makes handling errors much easier.
//				String q = "MATCH (n:MedicalCondition) RETURN n.name As name,n.type As name1";
            String q = "MATCH (A:MedicalSymptom),(B:MedicalCondition),p=(B)-[r:has_symptom]-(A) WHERE A.name contains '"+k1+"' RETURN A.name As name1, B.name As name  LIMIT 10";
            try (Transaction tx = session.beginTransaction()) {
                LOGGER.info(q);
                StatementResult result = tx.run(q);

                 while (result.hasNext()) {
                    Record record = result.next();
                    // Values can be extracted from a record by index or name.
//                    System.out.println(record.get("name").asString() + " " + record.get("name1").asString());
                    map.put(record.get("name").asString(), record.get("name1").asString());
//                    System.out.println(record.get("name1").asString()+" may have symptom "+ record.get("name").asString());
                }
                LOGGER.info("output :{}", map);
                LOGGER.info("Out of string loop");
                tx.success();  // Mark this write as successful.

                return new ProcessedInput(map);
            }
        }
    }

    public void close(Driver driver) {
        // Closing a driver immediately shuts down all open connections.
        LOGGER.info("Closing Driver Session");
        driver.close();
    }
}
