package com.stackroute.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.logging.Logger;

public class StopwordRemoval {

    private static Logger logger;




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

    public List<String> removeStopwords(List<String> terms){
        List<String> resultingdoc = new ArrayList<>();

        List<String> stopwords = readStopWords("stopwords.txt");


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
