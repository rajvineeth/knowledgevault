package com.stackroute.knowledgevault.queryengine;

import java.lang.String;

import com.stackroute.knowledgevault.queryengine.service.DriverInit;
import com.stackroute.knowledgevault.queryengine.service.QueryService;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class QueryEngineApplication {

	public static void main(String[] args) {
		QueryService queryService = new QueryService();
		DriverInit driver = new DriverInit("bolt://127.0.0.1:7687", "neo4j", "123456");
		Driver drive = driver.getDriver();
		queryService.loadgraph(drive);
		SpringApplication.run(QueryEngineApplication.class, args);
	}
}
