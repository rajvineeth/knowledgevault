package com.stackroute.knowledgevault.inputprocessor.utilities;

import com.stackroute.knowledgevault.domain.JSONld;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class FillUpDataTest {

    Logger LOGGER = LoggerFactory.getLogger(FillUpDataTest.class);
//    FillUpData fillUpData;
    @Before
    public void setUp() throws Exception {
//        fillUpData = new FillUpData();
    }

    @After
    public void tearDown() throws Exception {
//        fillUpData = null;
    }

    @Test
    public void fill() {
        Map<String, String> map = new HashMap<>();
        map.put("nose","body-part");
        map.put("bleeding", "symptom");
        map.put("diarrhoea", "disease");
        LOGGER.info(FillUpData.fill(map).toString());
        LOGGER.info("===###===\n\n{}",new JSONld((JSONObject) FillUpData.fill(map)));
    }
}