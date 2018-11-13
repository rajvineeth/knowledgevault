package com.stackroute;

import com.stackroute.algos.FullTextSearch;
import com.stackroute.algos.FullTextSearchImpl;
import com.stackroute.utilities.SentenceTokenizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class Processor {

    private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class);
    private SentenceTokenizer sentenceTokenizer;

//    @Autowired
    private FullTextSearch fullTextSearch = new FullTextSearchImpl();

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


    /**
     * This utility function spits out the relevant information
     * with proper tagging from the input paragraph
     * @param paragraph: the input paragraph
     * @return: tagged keywords that makes sense
     */
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

    public void mapping(String keyword) {
        this.getFullTextSearch().setFilesPath("paragraph-processor/src/main/java/com/stackroute/assets");
        this.getFullTextSearch().setIndexPath("paragraph-processor/src/main/java/com/stackroute/indices/paraIndex");

        this.getFullTextSearch().search(keyword);
    }
}
