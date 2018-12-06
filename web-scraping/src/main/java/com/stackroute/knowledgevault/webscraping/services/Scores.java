package com.stackroute.knowledgevault.webscraping.services;

import org.jsoup.nodes.Document;

import java.util.List;
import java.util.Map;

public interface Scores {
    public void calculateScore(Document document, String tag);
    public double tf(List<String> doc, String term);
    public Map<String, Double> getScoredDoc(String url);
    public void readJSON();
}
