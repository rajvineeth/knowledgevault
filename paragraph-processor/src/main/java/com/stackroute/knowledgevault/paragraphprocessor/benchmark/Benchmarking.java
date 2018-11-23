package com.stackroute.knowledgevault.paragraphprocessor.benchmark;

import com.stackroute.knowledgevault.paragraphprocessor.Processor;
import java.util.*;

/**
 * Class  to benchmark the performance of Lucene in indexing and extracting relevant keywords from an input text
 * based on a given stored data Repositories
 */
public class Benchmarking {

    private Processor processor;
    private String filepath;
    private String indexPath;

    public void init() {
        this.filepath = "../paragraph-processor/assets/medicalRepositories";
        this.indexPath = "../paragraph-processor/assets/repoIndices";
        this.processor = new Processor(filepath,indexPath);
    }

    /**
     * Query for presence of a particular keyword in the repositories and get its score,location
     * and other document related information
     * @param keyword: the input keyword that we want to search
     */
    public List<String> queryForGivenKeyword(String keyword) {
        this.processor.getFullTextSearch().indexer(this.filepath,this.indexPath);
        return this.processor.getFullTextSearch().search(this.indexPath,keyword);
    }

    /**
     * this function gives a list of relevant keywords in a given document corresponding to a corpus of documents
     * @param path: the path of the document that we want to search
     * @return: the list of relevant keywords
     */
    public List<String> getRelevantTerms(String path,int type) {
        init();
        return this.processor.getFullTextSearch().getRelevantTerms(this.filepath,this.indexPath,path,type);
    }

}
