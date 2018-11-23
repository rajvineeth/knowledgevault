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
        this.benchmarking.init();
        assertNotEquals(list,this.benchmarking.queryForGivenKeyword("cough"));
    }

    @Test
    public void getRelevantTermsTest() {
        String str1 = "[liver, cancer, of, the, in, and, a, disease, to, cancers, with, or, children, is, as, for, health, from, are, symptoms, on, adults, that, by, be, may, swelling, an, below, skin]";
        String str2 = this.benchmarking.getRelevantTerms("../paragraph-processor/assets/TB",1).toString();
        assertEquals(str1,str2);
    }

}
