package com.stackroute.knowledgevault.paragraphprocessor.algos;

import edu.stanford.nlp.simple.Document;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class POSTaggingTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(POSTaggingTest.class);

    @Test
    public void posTest() {
        String txt = "i am#low#cardiac arrest#luck.";
        LOGGER.info(POSTagging.tagger(txt,"#").toString());
    }

    @Test
    public void lemmaTest() {
        String txt = "i#have#cancers#in#my#lungs#i like#running";
        String[] arr = txt.split("#");
        List<String> lemmas = new ArrayList();
        for(String str: arr) {
            if(!str.contains(" ")) {
                Document doc = new Document(str);
                lemmas.add(doc.sentence(0).lemmas().get(0));
            }
        }
        LOGGER.info(lemmas.toString());
    }
}