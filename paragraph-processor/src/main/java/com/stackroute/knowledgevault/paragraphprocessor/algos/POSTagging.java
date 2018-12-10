package com.stackroute.knowledgevault.paragraphprocessor.algos;

import edu.stanford.nlp.simple.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class POSTagging {

    private POSTagging() {}
    private static final Logger LOGGER = LoggerFactory.getLogger(POSTagging.class);

    /**
     * This function performs the prt-of-speech tagging of each word in a given text file
     * @param txt: the input text file
     * @param regex: the regular expression which splits the String
     * @return: a collection of keywords with each word having a POS-tag;
     */
    public static Map<String,String> tagger(String txt,String regex) {
        Map<String,String> res = new HashMap<>();
        String text = txt.replaceAll("\\.","");
        String[] arr = text.split(regex);
        for(String token: arr) {
            LOGGER.info("sentence: {}", token);
            Document doc = new Document(token);
            res.put(doc.text(),doc.sentence(0).posTag(0));
        }
        return res;
    }

    public static String individualTag(String key) {
        Document doc = new Document(key);
        return doc.sentences().get(0).posTag(0);
    }
}
