package com.stackroute.knowledgevault.paragraphprocessor;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.Version;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class MyAnalyzerTest {

    private MyAnalyzer myAnalyzer;
    private static final Logger LOGGER = LoggerFactory.getLogger(MyAnalyzerTest.class);

    @Before
    public void setup() {
        this.myAnalyzer = new MyAnalyzer();
    }

    @After
    public void destroy() {
        this.myAnalyzer = null;
    }

    @Test
    public void myAnalyzerTest() {
        List<String> result = new ArrayList<>();
        TokenStream stream  = null;
        try {
            stream = this.myAnalyzer.tokenStream(null, new StringReader(" i have cancer in Lungs."));
            stream.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            while(stream.incrementToken()) {
                result.add(stream.getAttribute(CharTermAttribute.class).toString());
            }

        }
        catch(IOException e) {
            // not thrown b/c we're using a string reader...
        }

        LOGGER.info("tokens of the text: {} ",result.toString());
    }

    @Test
    public void indexTest() throws IOException {
        StringBuilder sb = new StringBuilder();
        Directory dir = new RAMDirectory();
        Analyzer analyzer = new MyAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_CURRENT,analyzer);
        IndexWriter writer = new IndexWriter(dir,config);

        Document doc = new Document();
        doc.add(new Field("content","i have cancer in my lungs", Field.Store.YES, Field.Index.ANALYZED));
        writer.addDocument(doc);
        writer.commit();
        IndexReader iReader = IndexReader.open(dir);

        Fields fields = MultiFields.getFields(iReader);
        Iterator<String> iterator = fields.iterator();

        while (iterator.hasNext()) {
            String field = iterator.next();
            Terms terms = MultiFields.getTerms(iReader, field);
            TermsEnum it = terms.iterator(null);
            BytesRef term = it.next();
            while (term != null) {
                String data = term.utf8ToString();
                LOGGER.info(data);
                sb.append(data+"\n");
                term = it.next();
            }
        }

        assertEquals("cancer\nhave\ni\nin\nlung\nmy\n",sb.toString());
    }

}