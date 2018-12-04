package com.stackroute.knowledgevault.queryengine.service;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;

public class DriverInit {
    Driver driver;
    public DriverInit(String uri, String user, String password)
    {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }

    public Driver getDriver() {
        return driver;
    }
}
