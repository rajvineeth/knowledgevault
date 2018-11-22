package com.stackroute.knowledgevault;

import com.stackroute.knowledgevault.paragraphprocessor.Processor;
import com.stackroute.knowledgevault.paragraphprocessor.utilities.DocProcessor;
import com.stackroute.knowledgevault.paragraphprocessor.utilities.Pair;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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
        this.processor.setFilePath("../paragraph-processor/assets/taggerResource");
        this.processor.setIndexPath("../paragraph-processor/assets/taggerIndices");
        this.processor.getFullTextSearch().indexer(this.processor.getFilePath(),this.processor.getIndexPath());
        String paragraph = "my name is neeraj and i have been suffering from cancer in my lungs. I also have toothache for past 5days";
        LOGGER.info(paragraph);
        this.processor.paraProcessing(paragraph);
    }

    @Test
    public void keywordMapping() {
        this.processor.setFilePath("../paragraph-processor/assets/taggerResource/");
        this.processor.setIndexPath("../paragraph-processor/assets/taggerIndices");
        this.processor.getFullTextSearch().indexer(this.processor.getFilePath(),this.processor.getIndexPath());
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
        Map<String,List<Pair>> tags = new HashMap<>();
        String para = "i have cancer in my lungs.typhoid cough.Migraine.";

        Map<String,Double> keys = DocProcessor.performNGramAnalysis(para);
        LOGGER.info("returned keys: {}",keys.keySet());

        for(Map.Entry<String,Double> key: keys.entrySet()) {

            File dictionary = new File("../../knowledge-vault/paragraph-processor/assets/taggerResource/");
            for(File f: dictionary.listFiles()) {

                tags.putIfAbsent(f.getName(),new ArrayList<>());
                try(BufferedReader br = new BufferedReader(new FileReader(f))) {
                    String txt;
                    while((txt = br.readLine())!=null) {
                        if(txt.compareTo(key.getKey())==0) {
                            tags.get(f.getName()).add(new Pair(key.getKey(),key.getValue()));
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        LOGGER.info(tags.toString());
    }
}