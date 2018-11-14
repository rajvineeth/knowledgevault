package com.stackroute.knowledgevault;

import com.stackroute.knowledgevault.algos.FullTextSearch;
import com.stackroute.knowledgevault.algos.FullTextSearchImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Processor {

    private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class);

    private FullTextSearch fullTextSearch;

    public void setFullTextSearch(FullTextSearch fullTextSearch) {
        this.fullTextSearch = fullTextSearch;
    }


    public FullTextSearch getFullTextSearch() {
        return this.fullTextSearch;
    }

    /**
     * Initialisation function which sets up the Lucene full text search functionalities
     */
    public void initProcessor() {
        this.getFullTextSearch().setFilesPath("src/main/java/com/stackroute/knowledgevault/assets/taggerResource");
        this.getFullTextSearch().setIndexPath("src/main/java/com/stackroute/knowledgevault/indices/dictionaries");
        this.getFullTextSearch().indexer();
    }

    /**
     * This utility function spits out the relevant information
     * with proper tagging from the input paragraph
     * @param paragraph: the input paragraph
     * @return: tagged keywords that makes sense
     */
    public Map<String,String> paraProcessing(String paragraph) {
        Map<String,String> tags = new HashMap<>();
//        this.getFullTextSearch().indexer();
//        List<String> keywords = this.getFullTextSearch().getRelevantTerms(paragraph,0);
        String[] keywords = paragraph.trim().split("\\.|\\s+");
        for(String keyword: keywords) {
            tags.put(keyword,keywordMapping(keyword));
        }
//        LOGGER.info("tag for lung: {}", keywordMapping("lung"));
        for (Map.Entry<String,String> entry : tags.entrySet()) {
            LOGGER.info("word = {}, tag = {}", entry.getKey(), entry.getValue());
        }
        return tags;
    }

    public String keywordMapping(String keyword) {
        return this.getFullTextSearch().search(keyword).get(0);
    }
}
