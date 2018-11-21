package com.stackroute.knowledgevault;

import com.stackroute.knowledgevault.paragraphprocessor.Processor;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
        assertEquals("not found",this.processor.patMatch(dur));
    }

    @Test
    public void teste() {
        Map<String,String> tags = new HashMap<>();
        String para = "i have cancer in my lungs";
        String[] keys = para.trim().split("\\.|\\s+");
        for(String key:keys) {
            File dictionary = new File("../../knowledge-vault/paragraph-processor/assets/taggerResource/");
            File[] res = dictionary.listFiles();
            for(File f: res) {
                try {
                    String txt = FileUtils.readFileToString(f,"UTF-8");
                    String[] words = txt.split("\\.|\\s+");
                    for(String word: words) {
                        if(word.compareTo(key)==0) tags.put(key,f.getName());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        LOGGER.info(tags.toString());
    }
}