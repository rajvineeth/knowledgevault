package com.stackroute.knowledgevault.paragraphprocessor;

import com.stackroute.knowledgevault.paragraphprocessor.algos.FullTextSearch;
import com.stackroute.knowledgevault.paragraphprocessor.algos.FullTextSearchImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;

public class Processor {

    private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class);

    private FullTextSearch fullTextSearch;

    public Processor() {
        this.fullTextSearch = new FullTextSearchImpl();
    }

    public void setFullTextSearch(FullTextSearch fullTextSearch) {
        this.fullTextSearch = fullTextSearch;
    }

    public FullTextSearch getFullTextSearch() {
        return this.fullTextSearch;
    }

    /**
     * Initialisation function which sets up the Lucene full text search functionality
     */
    public void initProcessor() {
        this.fullTextSearch.setFilesPath("src/main/java/com/stackroute/knowledgevault/paragraphprocessor/assets/taggerResource");
        this.fullTextSearch.setIndexPath("src/main/java/com/stackroute/knowledgevault/paragraphprocessor/assets/taggerIndices");
        this.fullTextSearch.indexer();
    }

    /**
     * This utility function spits out the relevant information
     * with proper tagging from the input paragraph
     * @param paragraph: the input paragraph
     * @return: tagged keywords that makes sense
     */
    public Map<String,String> paraProcessing(String paragraph) {
        initProcessor();
        Map<String,String> tags = new HashMap<>();
        String[] keywords = paragraph.toLowerCase().trim().split("\\.|\\s+");
        for(String keyword: keywords) {
            tags.put(keyword,keywordMapping(keyword));
        }
        for (Map.Entry<String,String> entry : tags.entrySet()) {
            LOGGER.info("{  word : {} , tag : {} }", entry.getKey(), entry.getValue());
        }
        return tags;
    }

    public String keywordMapping(String keyword) {
        return this.fullTextSearch.search(keyword).get(0);
    }
}
