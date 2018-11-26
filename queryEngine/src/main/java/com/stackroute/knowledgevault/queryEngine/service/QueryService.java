package com.stackroute.knowledgevault.queryEngine.service;

import org.neo4j.driver.v1.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileReader;

public class QueryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryService.class);
//    private DriverInit driver = new DriverInit("bolt://localhost:7687", "neo4j", "123456");
//    Driver drive = driver.getDriver();
    public void runquery(Driver driver, String k1, String k2) {
        LOGGER.info("received keywords from tagger");
        // Sessions are lightweight and disposable connection wrappers.
        try (Session session = driver.session()) {
            // Wrapping Cypher in an explicit transaction provides atomicity
            // and makes handling errors much easier.
//				String q = "MATCH (n:MedicalCondition) RETURN n.name As name,n.type As name1";
            String q = "MATCH (A:MedicalSymptom),(B:MedicalCondition),p=()-[r:has_symptom]-(A) WHERE A.name CONTAINS "+"'"+k2+"'" +"OR B.name CONTAINS " + "'"+k1+"'"+" RETURN A.name As name,B.name As name1 LIMIT 1";
            try (Transaction tx = session.beginTransaction()) {
                LOGGER.info(q);
                StatementResult result = tx.run(q);
                while (result.hasNext()) {
                    Record record = result.next();
                    // Values can be extracted from a record by index or name.
                    System.out.println(record.get("name").asString() + " "+record.get("name1").asString());
                }
                LOGGER.info("Out of string loop");
                tx.success();  // Mark this write as successful.
            }
        }
    }
    public void close (Driver driver)
    {
        // Closing a driver immediately shuts down all open connections.
        LOGGER.info("Closing Driver Session");
        driver.close();
    }
}
