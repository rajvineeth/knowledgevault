package com.stackroute.knowledgevault.utilities;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class SentenceTokenizerTest {

    private SentenceTokenizer sentenceTokenizer;
    private String path;
    @Before
    public void init() throws IOException {
        this.path = "src/main/resources/sentence_break_rules.txt";
        this.sentenceTokenizer = new SentenceTokenizer(this.path);
    }

    @After
    public void destroy() {
        this.sentenceTokenizer = null;
    }

    @Test
    public void setTextTest() {
        String txt1 = "some random sentence";
        this.sentenceTokenizer.setText(txt1);
        assertEquals(txt1,this.sentenceTokenizer.getText());
    }

    @Test
    public void nextSentenceTest() {

        String paragraph = "sentence number 1. sentence number 2. sentence number 3.";
        this.sentenceTokenizer.setText(paragraph);
        System.out.println(this.sentenceTokenizer.getText());
        String sentence=null;
        int count=0;
        while((sentence = this.sentenceTokenizer.nextSentence())!=null) {
            count++;
//            System.out.println(sentence);
            System.out.println(this.sentenceTokenizer.getIndex());
//            assertEquals("sentence number "+count,sentence);
        }
    }
}