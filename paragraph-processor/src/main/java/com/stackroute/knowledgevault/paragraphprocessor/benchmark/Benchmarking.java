package com.stackroute.knowledgevault.paragraphprocessor.benchmark;

import com.stackroute.knowledgevault.paragraphprocessor.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;

/**
 * Class  to benchmark the performance of Lucene in indexing and extracting relevant keywords from an input text
 * based on a given stored data Repositories
 */
public class Benchmarking {

    private Processor processor;
    private static final Logger LOGGER = LoggerFactory.getLogger(Benchmarking.class);

    public void init() {
        this.processor = new Processor();
        this.processor.getFullTextSearch().setFilesPath("src/main/java/com/stackroute/knowledgevault/paragraphprocessor/assets/medicalRepositories");
        this.processor.getFullTextSearch().setIndexPath("src/main/java/com/stackroute/knowledgevault/paragraphprocessor/assets/repoIndices");
    }

    /**
     * Query for presence of a particular keyword in the repositories and get its score,location
     * and other document related information
     * @param keyword: the input keyword that we want to search
     */
    public List<String> queryForGivenKeyword(String keyword) {
        init();
        this.processor.getFullTextSearch().indexer();
        return this.processor.getFullTextSearch().search(keyword);
    }

    /**
     * this function gives a list of relevant keywords in a given document corresponding to a corpus of documents
     * @param path: the path of the document that we want to search
     * @return: the list of relevant keywords
     */
    public List<String> getRelevantTerms(String path,int type) {
        init();
        return this.processor.getFullTextSearch().getRelevantTerms(path,type);
    }

}
