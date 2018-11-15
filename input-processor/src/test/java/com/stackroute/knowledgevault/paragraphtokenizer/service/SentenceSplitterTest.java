package com.stackroute.knowledgevault.paragraphtokenizer.service;

import com.stackroute.knowledgevault.domain.Input;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SentenceSplitterTest {

    SentenceSplitter sentenceSplitter;

    @Before
    public void setUp() throws Exception {
        sentenceSplitter = new SentenceSplitter();
    }

    @After
    public void tearDown() throws Exception {
        sentenceSplitter = null;
    }

    @Test
    public void splitDocument() {
        Input input = new Input("My name is Shubham Dutta. I am 24 years old. I have an acute pain in my stomach accompanied by dysentery. There is pain in my lower back-bone. I've also vomited a couple of times. I had a 100F fever last night, so I took paracitamol to bring the fever down.");
        List list = new ArrayList();
        list.add("My name is Shubham Dutta.");
        list.add("I am 24 years old.");
        list.add("I have an acute pain in my stomach accompanied by dysentery.");
        list.add("There is pain in my lower back-bone.");
        list.add("I've also vomited a couple of times.");
        list.add("I had a 100F fever last night, so I took paracitamol to bring the fever down.");

        Assert.assertEquals(list, sentenceSplitter.splitDocument(input));
    }
}