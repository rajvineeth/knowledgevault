package com.stackroute.knowledgevault.inputprocessor.utilities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static org.junit.Assert.*;

public class ProcessorTest {

    Processor processor;
    Logger LOGGER = LoggerFactory.getLogger(ProcessorTest.class);

    @Before
    public void setUp() throws Exception {
        processor = new Processor();
    }

    @After
    public void tearDown() throws Exception {
        processor = null;
    }

    @Test
    public void paraProcessing() {
//        String str = "My name is Shubham Dutta and I am 24 years old. Since last month, I have been experiencing excruciating pain time to time in my abdomen. And for a week now, a lot of my hair are falling. I have blisters on my back. I sweat a lot in my sleep.";
//
//        Map map = processor.paraProcessing(str);
//
//        LOGGER.info(map.toString());
    }

    @Test
    public void keywordMapping() {
    }
}