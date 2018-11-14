package com.stackroute.knowledgevault;

import com.stackroute.knowledgevault.algos.FullTextSearch;
import com.stackroute.knowledgevault.algos.FullTextSearchImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

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
     * This utility function spits out the relevant information
     * with proper tagging from the input paragraph
     * @param paragraph: the input paragraph
     * @return: tagged keywords that makes sense
     */
    public String paraProcessing(String paragraph) throws IOException {

        return null;
    }

    public void mapping(String keyword) {
        this.getFullTextSearch().setFilesPath("paragraph-processor/src/main/java/com/stackroute/taggerResource");
        this.getFullTextSearch().setIndexPath("paragraph-processor/src/main/java/com/stackroute/indices/dictionaries");

        this.getFullTextSearch().search(keyword);
    }
}
