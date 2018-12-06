package com.stackroute.knowledgevault.webscraping.services;

import java.util.List;
import java.util.Map;

public interface webScraping {
    public void getURL(String url);
    public Map<String, Double> getScoredDocs();
    public String getTitle();
    public String getEvaluatedTitle();
    public String getDescription();
}
