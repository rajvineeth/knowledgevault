package com.stackroute;

import com.stackroute.algos.FullTextSearch;
import com.stackroute.algos.FullTextSearchImpl;
import com.stackroute.utilities.SentenceTokenizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Processor {

    private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class);
    private FullTextSearch fullTextSearch = new FullTextSearchImpl();
    private SentenceTokenizer sentenceTokenizer;

    public void setFullTextSearch(FullTextSearch fullTextSearch) {
        this.fullTextSearch = fullTextSearch;
    }

    public SentenceTokenizer getSentenceTokenizer() {
        return sentenceTokenizer;
    }

    public void setSentenceTokenizer(SentenceTokenizer sentenceTokenizer) {
        this.sentenceTokenizer = sentenceTokenizer;
    }

    public FullTextSearch getFullTextSearch() {
        return this.fullTextSearch;
    }

    public String paraProcessing(String paragraph) {

        String response;
        this.sentenceTokenizer = new SentenceTokenizer();
        this.sentenceTokenizer.setText(paragraph);
        String sentence=null;
        int sentenceNumber=0;
        while((sentence = sentenceTokenizer.nextSentence())!=null) {
            // do processing stuff
            sentenceNumber++;
        }
        return null;
    }


}
