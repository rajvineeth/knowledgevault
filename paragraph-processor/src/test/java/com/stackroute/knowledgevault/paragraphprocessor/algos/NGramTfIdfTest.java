package com.stackroute.knowledgevault.paragraphprocessor.algos;

import com.google.common.collect.Lists;
import com.stackroute.knowledgevault.paragraphprocessor.utilities.DocProcessor;
import org.apache.kafka.common.protocol.types.Field;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static java.util.stream.Collectors.toMap;

public class NGramTfIdfTest {

    private NGramTfIdf nGramTfIdf;
    private static final Logger LOGGER  = LoggerFactory.getLogger(NGramTfIdf.class);


    @Before
    public void init() {
        this.nGramTfIdf = new NGramTfIdf();
    }

    @After
    public void destroy() {
        this.nGramTfIdf = null;
    }

    @Test
    public void testNgramTfIdf() {

        List<Integer> ns = new ArrayList(){{
            for(int i=0;i<3;i++) add(i+1);
        }};

        TfIdf.Normalization normalization = TfIdf.Normalization.COSINE;
        boolean smooth = false;
        boolean noAddOne = false;

        String para = "i have cancer. it is in my lungs";
        List<String> text = Arrays.asList(para.trim().split("\\.|\\n"));

        Iterable<Collection<String>> documents = nGramTfIdf.ngramDocumentTerms(ns, text);

        Iterable<Map<String, Double>> tfs = TfIdf.tfs(documents);
        Map<String, Double> idf = TfIdf.idfFromTfs(tfs, smooth, !noAddOne);
        LOGGER.info("TF-IDF scores: ");
        Map<String,Double> res=new HashMap<>();

        for (Map<String, Double> tf : tfs) {
            Map<String, Double> tfIdf = TfIdf.tfIdf(tf, idf, normalization);
            Map<String,Double> tmp = DocProcessor.sortByValues(tfIdf);
            LOGGER.info(tmp.toString());
            int i=0;
            for(Map.Entry<String,Double> entry : tmp.entrySet()) {
                if(i<Math.min(20,tmp.size())) {
                    res.put(entry.getKey(),entry.getValue());
                    i++;
                }
            }
        }

        LOGGER.info("\nscore details of individual docs: {}\n", DocProcessor.sortByValues(res));

    }

}