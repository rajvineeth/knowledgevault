package com.stackroute.knowledgevault.paragraphprocessor;

import com.stackroute.knowledgevault.paragraphprocessor.algos.FullTextSearch;
import com.stackroute.knowledgevault.paragraphprocessor.algos.FullTextSearchImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Processor {

    private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class);

    private FullTextSearch fullTextSearch = new FullTextSearchImpl();

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
        this.getFullTextSearch().setFilesPath("src/main/java/com/stackroute/knowledgevault/paragraphprocessor/assets/taggerResource");
        this.getFullTextSearch().setIndexPath("src/main/java/com/stackroute/knowledgevault/paragraphprocessor/assets/taggerIndices");
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
        List<String> keywords = this.getFullTextSearch().getRelevantTerms(paragraph,0);
        for(String keyword: keywords) {
            tags.put(keyword,keywordMapping(keyword));
        }
        for (Map.Entry<String,String> entry : tags.entrySet()) {
            LOGGER.info("{  word : {} , tag : {} }", entry.getKey(), entry.getValue());
        }
        return tags;
    }

    public String keywordMapping(String keyword) {
        return this.getFullTextSearch().search(keyword).get(0);
    }
}
