package com.stackroute.knowledgevault.paragraphprocessor;

import com.stackroute.knowledgevault.paragraphprocessor.utilities.DocProcessor;
import com.stackroute.knowledgevault.paragraphprocessor.utilities.FillUpData;
import com.stackroute.knowledgevault.paragraphprocessor.utilities.Pair;
import edu.stanford.nlp.simple.Document;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
    public void jsonldGenTest() {
        Map<String,List<Pair>> tags = new HashMap<>();
        String para = "The thirteen common communicable diseases found in India are as follows: 1. Malaria 2. Typhoid 3. Hepatitis 4. Jaundice 5. Leptospirosis 6. Diarrhoeal Diseases 7. Amoebiasis 8. Cholera 9. Brucellosis 10. Hookworm Infection 11. Influenza 12. Filariasis 13. Tuberculosis.";

        Map<String,Double> keys = DocProcessor.performNGramAnalysis(para);
        LOGGER.info("returned keys: {}",keys.keySet());

        for(Map.Entry<String,Double> key: keys.entrySet()) {

            File dictionary = new File("../../knowledge-vault/paragraph-processor/assets/taggerResource/");
            for(File f: dictionary.listFiles()) {

                tags.putIfAbsent(f.getName(),new ArrayList<>());
                try(BufferedReader br = new BufferedReader(new FileReader(f))) {
                    String txt;
                    while((txt = br.readLine())!=null) {
                        if(txt.length()==0) continue;
                        String l1 = new Document(txt).sentences().get(0).lemmas().get(0);
                        String l2 = new Document(key.getKey()).sentences().get(0).lemmas().get(0);
                        if(l1!=null && l1.compareToIgnoreCase(l2)==0) {
                            tags.get(f.getName()).add(new Pair(key.getKey(),key.getValue()));
                            break;
                        }
                    }
                } catch( Exception e) {
                    e.printStackTrace();
                }
            }
        }
        LOGGER.info(tags.toString());

        JSONObject obj = FillUpData.fillOntology(tags);
    }
}