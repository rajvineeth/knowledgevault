package com.stackroute.knowledgevault.paragraphprocessor.utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class fillUpDataTest {

    private FillUpData fillUpData;

    @Before
    public void init() {
        this.fillUpData = new FillUpData();
    }

    @After
    public void destry() {
        this.fillUpData = null;
    }

    @Test
    public void fillTest(){
        Map<String,String> map = new HashMap<>();
        map.put("cancer","disease");
        map.put("from","not found");
        map.put("me","pronoun");
        map.put("lungs","body-part");

        this.fillUpData.fill(map);
    }
}