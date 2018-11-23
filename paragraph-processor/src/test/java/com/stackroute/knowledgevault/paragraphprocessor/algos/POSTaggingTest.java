package com.stackroute.knowledgevault.paragraphprocessor.algos;

import com.stackroute.knowledgevault.paragraphprocessor.algos.POSTagging;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

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
        Map<String,String> res = POSTagging.tagger(str);
        Map<String,String> map = new HashMap(){{
            put("Im","NN");
            put("so","RB");
            put("happy","JJ");
            put("about","IN");
            put("my","PRP$");
            put("marks","NNS");
        }};
        assertEquals(map.toString(),res.toString());
    }

}