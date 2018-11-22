package com.stackroute.knowledgevault.inputprocessor.analyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.KeywordTokenizer;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.snowball.SnowballFilter;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.standard.StandardFilter;

import java.io.Reader;

public class ResourceAnalyzer extends Analyzer {
    /**
     * Creates a new {@link TokenStreamComponents} instance for this analyzer.
     *
     * @param fieldName the name of the fields content passed to the
     *                  {@link TokenStreamComponents} sink as a reader
     * @param reader    the reader passed to the {@link //Tokenizer} constructor
     * @return the {@link TokenStreamComponents} for this analyzer.
     */
    @Override
    protected TokenStreamComponents createComponents(String fieldName, Reader reader) {
        KeywordTokenizer tokenizer = new KeywordTokenizer(reader);
        TokenStream tokenStream = new StandardFilter(tokenizer);
        tokenStream = new LowerCaseFilter(tokenStream);
        tokenStream = new StopFilter(tokenStream, StandardAnalyzer.STOP_WORDS_SET);
        tokenStream = new SnowballFilter(tokenStream, "English");
        return new TokenStreamComponents(tokenizer, tokenStream);
    }
}
