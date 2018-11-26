package com.stackroute.knowledgevault.paragraphprocessor;

import com.stackroute.knowledgevault.paragraphprocessor.algos.FullTextSearch;
import com.stackroute.knowledgevault.paragraphprocessor.algos.FullTextSearchImpl;
import com.stackroute.knowledgevault.paragraphprocessor.algos.POSTagging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Processor {

    private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class);
    private String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getIndexPath() {
        return indexPath;
    }

    public void setIndexPath(String indexPath) {
        this.indexPath = indexPath;
    }

    private String indexPath;

    private FullTextSearchImpl fullTextSearch = new FullTextSearchImpl();

    public FullTextSearch getFullTextSearch() {
        return this.fullTextSearch;
    }

    public Processor(){}

    public Processor(String filePath,String indexPath) {
        this.filePath = filePath;
        this.indexPath = indexPath;
        this.getFullTextSearch().indexer(this.filePath,this.indexPath);
    }

    /**
     * This utility function spits out the relevant information
     * with proper tagging from the input paragraph
     * @param paragraph: the input paragraph
     * @return: tagged keywords that makes sense
     */
    public Map<String,String> paraProcessing(String paragraph) {

        Map<String,String> tags = new HashMap<>();
        Map<String,String> posTagged = POSTagging.tagger(paragraph," ");
        List<String> keywords = new ArrayList<>();
        for(Map.Entry<String,String> entry: posTagged.entrySet()) {
            String pos = entry.getValue();
            if(pos.compareTo("NN")==0 || pos.compareTo("NNS")==0 || pos.compareTo("JJ")==0) {
                keywords.add(entry.getKey());
            }
        }

        for(String keyword: keywords) {
            String tag = keywordMapping(keyword);
            LOGGER.info("tagged value: {}",tag);
            tags.put(keyword,keywordMapping(keyword));
        }

        for (Map.Entry<String,String> entry : tags.entrySet()) {
            LOGGER.info("{  word : {} , tag : {} }", entry.getKey(), entry.getValue());
        }
        return tags;
    }

    public String patMatch(String key) {
        String pat = "^(\\d+)(?=([day]|[week]|[month]|[year]))";
        Pattern pattern = Pattern.compile(pat);
        if(pattern.matcher(key).matches()) {
            return "duration";
        }
        return "not found";
    }

    public String keywordMapping(String keyword) {
        String tag = getFullTextSearch().search(this.indexPath,keyword).get(0);
        LOGGER.info(tag);
        if(tag.compareTo("not found")==0) {
            return patMatch(tag);
        }
        return tag;
    }
}
