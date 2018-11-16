package com.stackroute.knowledgevault;

import com.stackroute.knowledgevault.paragraphprocessor.Processor;
import com.stackroute.knowledgevault.paragraphprocessor.algos.FullTextSearch;
import com.stackroute.knowledgevault.paragraphprocessor.algos.FullTextSearchImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProcessorTest {

    private Processor processor;

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
        String paragraph = "My name is neeraj. I am suffering from cancer.I have cancer in my lungs.";
        paragraph.toLowerCase();
        this.processor.paraProcessing(paragraph);
    }

    @Test
    public void keywordMapping() {
        String tag = this.processor.keywordMapping("cancer");
        assertEquals("disease",tag);
    }
}