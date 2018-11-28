package com.stackroute.knowledgevault.paragraphprocessor.algos;

import com.stackroute.knowledgevault.paragraphprocessor.utilities.DocProcessor;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.util.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.html.HtmlParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;
public class NGramTfIdfTest {

    private static final Logger LOGGER  = LoggerFactory.getLogger(NGramTfIdf.class);

    @Test
    public void testNgramTfIdf() {

        List<Integer> ns = new ArrayList(){{
            for(int i=0;i<3;i++) add(i+1);
        }};

        TfIdf.Normalization normalization = TfIdf.Normalization.COSINE;
        boolean smooth = false;
        boolean noAddOne = false;

        String para = "i have cancer. it is in my lungs.";
        List<String> text = Arrays.asList(para.trim().split("\\.|\\n"));

        Iterable<Collection<String>> documents = NGramTfIdf.ngramDocumentTerms(ns, text);

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

        Map<String,Double> sorted = DocProcessor.sortByValues(res);

        LOGGER.info("\nscore details of individual docs: {}\n", DocProcessor.sortByValues(res).keySet());

        String keySet = "[have cancer, have, cancer, it is, it is in, in, is in, in my lungs, my lungs, is, it, my, is in my, lungs, in my]";
        Assert.assertEquals(keySet,sorted.keySet().toString());
    }


    @Test
    public void populateData() {

        BodyContentHandler handler = new BodyContentHandler();

        Metadata metadata = new Metadata();
        FileInputStream inputstream = null;
        try {
            inputstream = new FileInputStream(new File(
                    "/home/cgi/Documents/knowledge-vault/paragraph-processor/assets/medicalRepositories/Appendicitis_ Causes, Symptoms, and Treatment.html"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ParseContext pcontext = new ParseContext();

        //Html parser
        HtmlParser htmlparser = new HtmlParser();

        try {
            htmlparser.parse(inputstream, handler, metadata,pcontext);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TikaException e) {
            e.printStackTrace();
        }
        System.out.println("Contents of the document:" + handler.toString());
//        System.out.println("Metadata of the document:");
//        String[] metadataNames = metadata.names();
//
//        for(String name : metadataNames) {
//            System.out.println(name + ":   " + metadata.get(name));
//        }
    }

}