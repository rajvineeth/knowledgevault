package com.stackroute.knowledgevault.populator.service;

import org.neo4j.driver.v1.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

public class QueryDriverService {
    private Driver driver;
    private static final Logger LOGGER = LoggerFactory.getLogger(QueryDriverService.class);

    public QueryDriverService(String uri, String user, String password)
    {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }
    public <T,S> Boolean createRel(T node1, String rel, S node2)  {
        try (Session session = driver.session()) {
            Method method1=node1.getClass().getMethod("getType",null);
            String type1= (String) method1.invoke(node1,null);
            Method method2=node2.getClass().getMethod("getType",null);
            String type2= (String) method2.invoke(node2,null);
            Method methodName1=node1.getClass().getMethod("getName",null);
            String typeName1= (String) methodName1.invoke(node1,null);
            Method methodName2=node2.getClass().getMethod("getName",null);
            String typeName2= (String) methodName2.invoke(node2,null);
            String q="MATCH (u:"+type1+"{type:"+"'"+type1+"'"+",name:"+"'"+typeName1+"'"+"}), (r:"+type2+" {type:"+"'"+type2+"'"+",name:"+"'"+typeName2+"'"+"})\n" +
                    "MERGE (u)-[:"+rel+"]->(r)";
            try (Transaction tx = session.beginTransaction()) {
                tx.run(q);
                tx.success();
                return true;
            }
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
            return false;
        }
    }
    public void close()
    {
        driver.close();
    }
}