package com.stackroute.knowledgevault.benchmark;

import com.stackroute.knowledgevault.Processor;
import org.apache.commons.io.FileUtils;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.spans.SpanQuery;
import org.apache.lucene.search.spans.SpanTermQuery;
import org.apache.lucene.store.FSDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.*;

public class Benchmarking {

    private Processor  processor;
    private static final Logger LOGGER = LoggerFactory.getLogger(Benchmarking.class);

    public void init() {
        this.processor = new Processor();
        processor.getFullTextSearch().setFilesPath("src/main/java/com/stackroute/knowledgevault/benchmark/files");
        processor.getFullTextSearch().setIndexPath("src/main/java/com/stackroute/knowledgevault/benchmark/indices");
    }

    public List<String> solve() {
        init();
        this.processor.getFullTextSearch().indexer();
        return this.processor.getFullTextSearch().search("cough");
    }

    public List<String> getRelevantTerms(String path) {
        init();
        return this.processor.getFullTextSearch().getRelevantTerms(path);
    }

}
