package com.stackroute.knowledgevault.webscraping.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Primary
public class webScrapingImpl implements webScraping {
    private Map<String, Double> TermWeights = new HashMap<>();
    private ScoresImpl scores = new ScoresImpl();
    String url;

    /*
    Function to calculate term frequency(tf) score of a term in a document. Takes all the terms in a doc and term to be calculated.
    Returns normalized tf score, i.e. dividing the term frequency with the total number of terms to keep the score < 1.
     */

    @Override
    public void getURL(String url){
        this.url = url;
    }

    @Override
    public Map<String, Double> getScoredDocs(){
        scores.readJSON();
        this.TermWeights = scores.getScoredDoc(url);
        return TermWeights;
    }

    @Override
    public String getTitle() {
        return scores.getTitle();
    }

    @Override
    public String getEvaluatedTitle() {

        Map.Entry<String,Double> entry = TermWeights.entrySet().iterator().next();
        return entry.getKey();
    }

    @Override
    public String getDescription() {
        return scores.getDescription();
    }
}
