package com.stackroute.algos;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class POSTaggingTest {

    POSTagging posTagging;

    @Before
    public void init() {
        this.posTagging = new POSTagging();
    }

    @After
    public void destroy() {
        this.posTagging = null;
    }

    @Test
    public void taggerTest() {
        String str = "Im so happy about my marks";
        String op1 = this.posTagging.tagger(str);
        String op2 = "Im/NN so/RB happy/JJ about/IN my/PRP$ marks/NNS";
        assertEquals(op1,op2);
    }
}