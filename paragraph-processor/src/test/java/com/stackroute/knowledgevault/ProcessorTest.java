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
        String paragraph = "ionizing radiation is not a particularly strong mutagen.[52] Residential exposure to radon gas, for example, has similar cancer risks as passive smoking.[52] Radiation is a more potent source of cancer when combined with other cancer-causing agents, such as radon plus tobacco smoke.[52] Radiation can cause cancer in most parts of the body, in all animals and at any age. Children and adolescents are twice as likely to develop radiation-induced leukemia as adults; radiation exposure before birth has ten times the effect";
        paragraph.toLowerCase();
        System.out.println(paragraph);
        this.processor.paraProcessing(paragraph);
    }

    @Test
    public void keywordMapping() {
        String tag = this.processor.keywordMapping("cancer");
        assertEquals("disease",tag);
    }
}