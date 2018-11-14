package com.stackroute.knowledgevault.service;

import com.stackroute.knowledgevault.domain.Input;
import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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

        Assert.assertEquals("sgvsv", simpleNLP.lemmatize(new Sentence("My name is Shubham Dutta.")));
//        Input input = new Input();
//        input.setText(new Document("My name is Shubham Dutta."));
////        I am 24 years old. I have an acute pain in my stomach accompanied by dysentery. There is pain in my lower back-bone. I've also vomited a couple of times. I had a 100F fever last night, so I took paracitamol to bring the fever down.");
//        Assert.assertEquals("sjsnsd", simpleNLP.lemmatize(input.getText().));
    }

    @Test
    public void posTag() {
//        Input input = new Input("My name is Shubham Dutta. I am 24 years old. I have an acute pain in my stomach accompanied by dysentery. There is pain in my lower back-bone. I've also vomited a couple of times. I had a 100F fever last night, so I took paracitamol to bring the fever down.");

    }

    @Test
    public void tokenize() {
//        Input input = new Input("My name is Shubham Dutta. I am 24 years old. I have an acute pain in my stomach accompanied by dysentery. There is pain in my lower back-bone. I've also vomited a couple of times. I had a 100F fever last night, so I took paracitamol to bring the fever down.");

    }
}