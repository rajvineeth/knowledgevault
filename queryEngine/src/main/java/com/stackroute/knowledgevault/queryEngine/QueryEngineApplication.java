package com.stackroute.knowledgevault.queryEngine;

import java.lang.String;

import com.stackroute.knowledgevault.queryEngine.service.DriverInit;
import com.stackroute.knowledgevault.queryEngine.service.QueryService;
import org.neo4j.driver.v1.Driver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QueryEngineApplication {

	public static void main(String[] args) { SpringApplication.run(QueryEngineApplication.class, args);
	}
}
