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
    public void solveTest() {

        List<String> list = new ArrayList(){{
            add("not found");
        }};
        assertNotEquals(list,this.benchmarking.solve());
    }

    @Test
    public void getRelevantTermsTest() {
        this.benchmarking.getRelevantTerms("src/main/java/com/stackroute/knowledgevault/benchmark/files/document5.txt");
    }

}