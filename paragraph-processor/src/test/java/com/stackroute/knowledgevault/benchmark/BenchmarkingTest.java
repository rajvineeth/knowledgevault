package com.stackroute.knowledgevault.benchmark;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BenchmarkingTest {

    private Benchmarking benchmarking;

    @Before
    public void init() {
        this.benchmarking = new Benchmarking();
    }

    @After
    public void destroy() {
        this.benchmarking = null;
    }

    @Test
    public void queryForGivenKeywordTest() {
        List<String> list = new ArrayList(){{
            add("not found");
        }};
        assertNotEquals(list,this.benchmarking.queryForGivenKeyword("cough"));
    }

    @Test
    public void getRelevantTermsTest() {
        for(int i=1;i<=5;i++)
            this.benchmarking.getRelevantTerms("src/main/java/com/stackroute/knowledgevault/benchmark/files/document"+i+".txt");
    }

}