package com.stackroute.knowledgevault.paragraphprocessor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.snowball.SnowballFilter;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;

import java.io.Reader;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyAnalyzer extends Analyzer {

    private int minGram=1;
    private int maxGram=20;


    /**
     * Creates a new {@link TokenStreamComponents} instance for this analyzer.
     *
     * @param fieldName the name of the fields content passed to the
     *                  {@link TokenStreamComponents} sink as a reader
     * @param reader    the reader passed to the {@link Tokenizer} constructor
     * @return the {@link TokenStreamComponents} for this analyzer.
     */

    @Override
    protected TokenStreamComponents createComponents(String fieldName, Reader reader) {
        StandardTokenizer st = new StandardTokenizer(reader);
        TokenStream filter = new StandardFilter(st);
        filter = new LowerCaseFilter(filter);
        filter = new SnowballFilter(filter,"English");
        return new TokenStreamComponents(st,filter);
    }
}
