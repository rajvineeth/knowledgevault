package com.stackroute.knowledgevault.paragraphtokenizer.service;

import edu.stanford.nlp.simple.Sentence;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SimpleNLPTest {

    SimpleNLP simpleNLP;

    @Before
    public void setUp() throws Exception {
        simpleNLP = new SimpleNLP();
    }

    @After
    public void tearDown() throws Exception {
        simpleNLP = null;
    }

    @Test
    public void lemmatize() {
        Sentence sentence = new Sentence("Ram is going to the Zoological garden");
        List list = new ArrayList();
        list.add("Ram");
        list.add("be");
        list.add("go");
        list.add("to");
        list.add("the");
        list.add("zoological");
        list.add("garden");

        Assert.assertEquals(list, simpleNLP.lemmatize(sentence));
    }

    @Test
    public void posTag() {
        Sentence sentence = new Sentence("Ram is going to the Zoological garden");
        List list = new ArrayList();
        list.add("NNP");
        list.add("VBZ");
        list.add("VBG");
        list.add("TO");
        list.add("DT");
        list.add("JJ");
        list.add("NN");

        Assert.assertEquals(list, simpleNLP.posTag(sentence));
    }

    @Test
    public void tokenize() {
        Sentence sentence = new Sentence("Ram is going to the Zoological garden");
        List list = new ArrayList();

        list.add("Ram");
        list.add("is");
        list.add("going");
        list.add("to");
        list.add("the");
        list.add("Zoological");
        list.add("garden");

        Assert.assertEquals(list,simpleNLP.tokenize(sentence));
    }

    @Test
    public void nerTagging() {
        Sentence sentence = new Sentence("Tulsi cures cancer");
        List list = new ArrayList();

        list.add("Ram");
        list.add("is");
        list.add("going");
        list.add("to");
        list.add("the");
        list.add("Zoological");
        list.add("garden");

        System.out.println( simpleNLP.nerTagging(sentence));
        Assert.assertEquals(list, simpleNLP.nerTagging(sentence));
    }
}