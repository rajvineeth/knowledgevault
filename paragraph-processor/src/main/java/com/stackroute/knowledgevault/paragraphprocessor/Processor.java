package com.stackroute.knowledgevault.paragraphprocessor;

import com.stackroute.knowledgevault.paragraphprocessor.algos.FullTextSearch;
import com.stackroute.knowledgevault.paragraphprocessor.algos.FullTextSearchImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;

public class Processor {

    private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class);

    private FullTextSearchImpl fullTextSearch = new FullTextSearchImpl();

    public void setFullTextSearch(FullTextSearchImpl fullTextSearch) {
        this.fullTextSearch = fullTextSearch;
    }

    public FullTextSearch getFullTextSearch() {
        return this.fullTextSearch;
    }

    public Processor() {
        getFullTextSearch().setFilesPath("src/main/java/com/stackroute/knowledgevault/paragraphprocessor/assets/taggerResource");
        getFullTextSearch().setIndexPath("src/main/java/com/stackroute/knowledgevault/paragraphprocessor/assets/taggerIndices");
        getFullTextSearch().indexer();
    }

    /**
     * This utility function spits out the relevant information
     * with proper tagging from the input paragraph
     * @param paragraph: the input paragraph
     * @return: tagged keywords that makes sense
     */
    public Map<String,String> paraProcessing(String paragraph) {
        Map<String,String> tags = new HashMap<>();
        String[] keywords = paragraph.trim().toLowerCase().split("\\.|\\s+");
        for(String keyword: keywords) {
            tags.put(keyword,keywordMapping(keyword));
        }
        for (Map.Entry<String,String> entry : tags.entrySet()) {
            LOGGER.info("{  word : {} , tag : {} }", entry.getKey(), entry.getValue());
        }
        return tags;
    }

    public String keywordMapping(String keyword) {
        return getFullTextSearch().search(keyword).get(0);
    }
}
