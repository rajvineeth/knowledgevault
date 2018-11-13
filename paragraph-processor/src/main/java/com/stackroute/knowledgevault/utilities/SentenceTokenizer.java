package com.stackroute.knowledgevault.utilities;

import java.io.File;
import java.io.IOException;
import java.text.BreakIterator;

import org.apache.commons.io.FileUtils;

import com.ibm.icu.text.RuleBasedBreakIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

/**
 * Tokenizes the input into sentences. Uses ICU4J's RuleBasedBreakIterator
 * with rule file adapted from a dump of RBBI.sentenceInstance.
 */
public class SentenceTokenizer {

    @Autowired
    private Environment env;

    private String text;

    public String getText() {
        return text;
    }

    private int index = 0;
    private String textPath;

    public int getIndex() {
        return this.index;
    }

    public String getTextPath() {
        return this.textPath;
    }

    private RuleBasedBreakIterator breakIterator;

    public SentenceTokenizer() {}

    public SentenceTokenizer(String path) throws IOException {
        super();
        this.textPath = path;
        this.breakIterator = new RuleBasedBreakIterator(
                FileUtils.readFileToString(
                        new File(this.textPath), "UTF-8"));
    }

    /**
     * Setter function
     * @param text: the text you want to set to
     */
    public void setText(String text) {
        this.text = text;
        this.breakIterator.setText(text);
        this.index = 0;
    }

    /**
     * this function returns the next sentence in the paragraph
     * @return: the next sentence in the paragraph
     */
    public String nextSentence() {
        int end = breakIterator.next();
        if (end == BreakIterator.DONE) {
            return null;
        }
        String sentence = text.substring(this.index, end);
        this.index = end;
        return sentence;
    }
}