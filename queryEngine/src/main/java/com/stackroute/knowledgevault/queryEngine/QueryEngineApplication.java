package com.stackroute.knowledgevault.queryEngine;

import com.stackroute.knowledgevault.queryEngine.service.DriverInit;
import com.stackroute.knowledgevault.queryEngine.service.QueryService;
import org.neo4j.driver.v1.*;
import java.lang.String;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class QueryEngineApplication {
////	Driver driver;
////
////	private QueryEngineApplication(String uri, String user, String password)
////	{
////		driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
////	}
//	public void runquery () {
////		QueryDriverService example = QueryDriverService("bolt://localhost", "neo4j", "123456");
////        example.createRel(medicalCondition,rel,medicalSymptom);
////        example.close();
//		// Sessions are lightweight and disposable connection wrappers.
//		try (Session session = driver.session()) {
//			// Wrapping Cypher in an explicit transaction provides atomicity
//			// and makes handling errors much easier.
////				String q = "MATCH (n:MedicalCondition) RETURN n.name As name,n.type As name1";
//			String q = "MATCH p=(a)-[r:has_symptom]->(b) RETURN a.name As name, b.name As name1, r.label As name2 LIMIT 25";
//			try (Transaction tx = session.beginTransaction()) {
//				StatementResult result = tx.run(q);
//				while (result.hasNext()) {
//					Record record = result.next();
//					// Values can be extracted from a record by index or name.
//					System.out.println(record.get("name").asString() + " "+record.get("name2").asString() +" "+ record.get("name1").asString());
//				}
//				tx.success();  // Mark this write as successful.
//			}
//		}
//	}
//	public void close ()
//	{
//		// Closing a driver immediately shuts down all open connections.
//		driver.close();
//	}


	public static void main(String[] args) {

		DriverInit driver = new DriverInit("bolt://localhost:7687", "neo4j", "123456");
		QueryService queryService = new QueryService();
		Driver drive = driver.getDriver();
		String k1="cough",k2="blood";
		queryService.runquery(drive, k1, k2);
		queryService.close(drive);

		SpringApplication.run(QueryEngineApplication.class, args);
	}
}
