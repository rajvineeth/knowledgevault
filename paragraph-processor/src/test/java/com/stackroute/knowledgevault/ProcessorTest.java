package com.stackroute.knowledgevault;

import com.stackroute.knowledgevault.paragraphprocessor.Processor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

public class ProcessorTest {

    private Processor processor;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessorTest.class);

    @Before
    public void setUp() {
        this.processor = new Processor();
    }

    @After
    public void tearDown() {
        this.processor = null;
    }

    @Test
    public void paraProcessingTest() {
        String paragraph = "my name is neeraj and i have been suffering from cancer in my lungs. I also have toothache for past 5days";
        LOGGER.info(paragraph);
        this.processor.paraProcessing(paragraph);
    }

    @Test
    public void keywordMapping() {
        String tag = this.processor.keywordMapping("cancer");
        assertEquals("diseases",tag);
    }

    @Test
    public void testPatternMatch() {
        String dur = "5days";
        assertEquals("duration",this.processor.patMatch(dur));
    }
}