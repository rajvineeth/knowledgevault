package com.stackroute.knowledgevault.populator.service;

import com.github.jsonldjava.utils.Obj;
import com.stackroute.knowledgevault.domain.MedicalCondition;
import com.stackroute.knowledgevault.domain.MedicalSymptom;
import org.neo4j.driver.v1.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.neo4j.driver.v1.Values.parameters;

public class QueryDriverService {
    Driver driver;

    public QueryDriverService(String uri, String user, String password)
    {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }
//    public void createRel(MedicalCondition medicalCondition, String rel, MedicalSymptom medicalSymptom) {
//        try (Session session = driver.session()) {
//            String q="MATCH (u:"+medicalCondition.getType()+"{type:"+"'"+medicalCondition.getType()+"'"+"}), (r:"+medicalSymptom.getType()+" {type:"+"'"+medicalSymptom.getType()+"'"+"})\n" +
//                    "MERGE (u)-[:"+rel+"]->(r)";
//            try (Transaction tx = session.beginTransaction()) {
//                tx.run(q);
//                tx.success();
//            }
//        }
//    }
//    public <T,S> void getRel(T node1, T node2) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        try (Session session = driver.session()) {
//            Method method1=node1.getClass().getMethod("getType",null);
//            String type1= (String) method1.invoke(node1,null);
//            Method method2=node2.getClass().getMethod("getType",null);
//            String type2= (String) method2.invoke(node2,null);
//            String q="MATCH (u:MT { type: {node1} })-[r1]-(relations:MTR)-[r2]->(MedicalCondition:MT { type: {node2} }) return relations";
//            String q="MATCH (u:"+type1+"{type:"+"'"+type1+"'"+"}), (r:"+type2+" {type:"+"'"+type2+"'"+"})\n" +
//                    "MERGE (u)-[:"+rel+"]->(r)";
//            try (Transaction tx = session.beginTransaction()) {
//                tx.run(q);
//                tx.success();
//            }
//        }
//    }
    public <T,S> void createRel(T node1, String rel, T node2) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        try (Session session = driver.session()) {
            Method method1=node1.getClass().getMethod("getType",null);
            String type1= (String) method1.invoke(node1,null);
            Method method2=node2.getClass().getMethod("getType",null);
            String type2= (String) method2.invoke(node2,null);
            System.out.println(type1+" "+type2);
            String q="MATCH (u:"+type1+"{type:"+"'"+type1+"'"+"}), (r:"+type2+" {type:"+"'"+type2+"'"+"})\n" +
                    "MERGE (u)-[:"+rel+"]->(r)";
            try (Transaction tx = session.beginTransaction()) {
                tx.run(q);
                tx.success();
            }
        }
    }
    public <T,S> boolean relExists(T node1, S node2) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        try (Session session = driver.session()) {
            // Wrapping Cypher in an explicit transaction provides atomicity
            // and makes handling errors much easier.
            Method method1=node1.getClass().getMethod("getType",null);
            String type1= (String) method1.invoke(node1,null);
            Method method2=node1.getClass().getMethod("getType",null);
            String type2= (String) method2.invoke(node1,null);

            System.out.println(type1+type2);
           String q="MATCH ( MC: "+type1+" {type:"+"'"+type1+"'"+"})-[r]->( MS: "+type2+"{type:"+"'"+type2+"'"+"})\n" +
                    "RETURN r";
            try (Transaction tx = session.beginTransaction()) {
                StatementResult statementResult=tx.run(q);
                if(statementResult!=null)
                    return true;
                tx.success();  // Mark this write as successful.
            }
        }
        return false;
    }
    public void close()
    {
        // Closing a driver immediately shuts down all open connections.
        driver.close();
    }
}
