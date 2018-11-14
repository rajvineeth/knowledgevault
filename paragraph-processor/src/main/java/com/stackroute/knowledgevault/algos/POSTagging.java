package com.stackroute.knowledgevault.algos;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;

public class POSTagging {

    private static final Logger LOGGER = LoggerFactory.getLogger(POSTagging.class);

    public POSTagging() {

       // default constructor
    }

    /**
     * This function performs the prt-of-speech tagging of each word in a given text file
     * @param txt: the input text file
     * @return: a string with each word having a tag;
     */
    public String tagger(String txt) {
        StringBuilder sb = new StringBuilder();
        Properties props = new Properties();
        props.setProperty("annotators","tokenize, ssplit, pos");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        Annotation annotation = new Annotation(txt);
        pipeline.annotate(annotation);
        List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
        for (CoreMap sentence : sentences) {
            for (CoreLabel token: sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                String word = token.get(CoreAnnotations.TextAnnotation.class);

                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                LOGGER.info("{}_{}",word,pos);
                sb.append(word+"/"+pos+" ");
            }
        }
        return sb.toString().trim();
    }
}
