package com.stackroute.knowledgevault.paragraphprocessor.benchmark;

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
        this.benchmarking.getRelevantTerms("/home/cgi/Documents/knowledge-vault/paragraph-processor/assets/TB",1);
    }

}
