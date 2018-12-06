package com.stackroute.knowledgevault.webscraping.services;

import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.logging.Logger;

/**
 * Helper class to read stopwords from stopwords.txt and remove the stopwords in a document.
 */
public class StopwordRemoval {


    private String stopWords="/knowledge-vault/web-scraping/stopwords.txt";

    private static Logger logger;

    /*
    Function to read stopwords from stopwords.txt. returns a list containing all the stopwords.
     */

    public static List<String> readStopWords(String stopWordsFilename)
    {
        List<String> stopWords = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(stopWordsFilename))) {
            String line = br.readLine();


            while (line != null) {
                stopWords.add(line);
                line = br.readLine();
            }

        }catch (Exception e){
           logger.info(e.getMessage());
        }


        return stopWords;
    }

    /*
    Function to remove stopwords from a document i.e. a list of tokenized and lemmatized words. Returns a new list
    free of stopwords
     */

    public List<String> removeStopwords(List<String> terms){
        List<String> resultingdoc = new ArrayList<>();

        List<String> stopwords = readStopWords(stopWords);


        for(String term: terms){
            boolean flag = true;
            for(String stopword: stopwords){
                if(term.equalsIgnoreCase(stopword)){
                    flag = false;
                    break;
                }
            }
            if(flag)
                resultingdoc.add(term);
        }
        return resultingdoc;
    }

}
