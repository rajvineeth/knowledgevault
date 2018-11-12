package com.stackroute;

import com.stackroute.algos.FullTextSearch;
import com.stackroute.algos.FullTextSearchImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Processor {

    private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class);
    private FullTextSearch fullTextSearch = new FullTextSearchImpl();

    public FullTextSearch getFullTextSearch() {
        return this.fullTextSearch;
    }


}
