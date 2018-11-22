package com.stackroute.knowledgevault.populator.service;

import org.neo4j.driver.v1.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class QueryDriverService {
    Driver driver;

    public QueryDriverService(String uri, String user, String password)
    {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }
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
    public void close()
    {
        driver.close();
    }
}