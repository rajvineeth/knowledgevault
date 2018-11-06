package com.stackroute.utilities;

import java.io.File;
import java.io.IOException;
import java.text.BreakIterator;

import org.apache.commons.io.FileUtils;

import com.ibm.icu.text.RuleBasedBreakIterator;

/**
 * Tokenizes the input into sentences. Uses ICU4J's RuleBasedBreakIterator
 * with rule file adapted from a dump of RBBI.sentenceInstance.
 */
public class SentenceTokenizer {

    private String text;
    private String textPath;
    private int index = 0;
    private RuleBasedBreakIterator breakIterator;

    public SentenceTokenizer() {}
    public SentenceTokenizer(String path) throws IOException {
        super();
        this.textPath = path;
        this.breakIterator = new RuleBasedBreakIterator(
                FileUtils.readFileToString(
                        new File(this.textPath), "UTF-8"));
    }

    public void setText(String text) {
        this.text = text;
        this.breakIterator.setText(text);
        this.index = 0;
    }

    public String nextSentence() {
        int end = breakIterator.next();
        if (end == BreakIterator.DONE) {
            return null;
        }
        String sentence = text.substring(index, end);
        index = end;
        return sentence;
    }
}