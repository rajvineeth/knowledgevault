//package com.stackroute.knowledgevault.populator.service;
//
//import com.stackroute.knowledgevault.domain.MedicalCondition;
//import com.stackroute.knowledgevault.domain.MedicalSymptom;
//import org.neo4j.driver.v1.*;
//
//import static org.neo4j.driver.v1.Values.parameters;
//
//public class QueryDriverService {
//    Driver driver;
//
//    public QueryDriverService(String uri, String user, String password)
//    {
//        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
//    }
//    public void createRel(MedicalCondition medicalCondition, String rel, MedicalSymptom medicalSymptom) {
//        // Sessions are lightweight and disposable connection wrappers.
//        try (Session session = driver.session()) {
//            // Wrapping Cypher in an explicit transaction provides atomicity
//            // and makes handling errors much easier.
//            String q="MATCH (u:"+medicalCondition.getType()+"{type:"+"'"+medicalCondition.getType()+"'"+"}), (r:"+medicalSymptom.getType()+" {type:"+"'"+medicalSymptom.getType()+"'"+"})\n" +
//                    "CREATE (u)-[:"+rel+"]->(r)";
//            try (Transaction tx = session.beginTransaction()) {
//                tx.run(q);
//                tx.success();  // Mark this write as successful.
//            }
//        }
//    }
//    public void close()
//    {
//        // Closing a driver immediately shuts down all open connections.
//        driver.close();
//    }
//}
