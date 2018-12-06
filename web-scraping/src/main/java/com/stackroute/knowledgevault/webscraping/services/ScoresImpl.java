package com.stackroute.knowledgevault.webscraping.services;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class ScoresImpl implements Scores {

    private static final Logger LOGGER = LoggerFactory.getLogger(webScrapingImpl.class);
    private HashMap<String, Double> TermWeights = new HashMap<>();
    private NGram nGram;
    private Map<String, Long> tagWeights = new HashMap<>();
    private Document document;
    private StopwordRemoval stopwordRemoval = new StopwordRemoval();
    private String title;

    @Override
    public void calculateScore(Document document, String tag) {
        List<String> getData = document.getElementsByTag(tag).eachText();
        for(String data: getData){
            /*
            Removing stopwords before forming ngrams
             */
            String[] term = data.split(" ");
            List<String> terms = Arrays.asList(term);
            terms = stopwordRemoval.removeStopwords(terms);
            StringBuilder stopwordRemoved = new StringBuilder();
            for(int i=0;i<terms.size();i++){
                stopwordRemoved.append(" " + terms.get(i));
            }
            /*
            Forming ngrams
             */
            nGram = new NGram(stopwordRemoved.toString(), 2);
            List<String> ngrams = nGram.list();
            for(String ngram: ngrams){
                double score = tf(ngrams, ngram);
                if(TermWeights.get(ngram) != null){
                    double tempscore = TermWeights.get(ngram) + tagWeights.get(tag)*score;
                    TermWeights.put(ngram, tempscore);
                }else{
                    TermWeights.put(ngram, tagWeights.get(tag)*score);
                }
            }
        }
    }

    @Override
    public double tf(List<String> doc, String term) {
        double result = 0;
        for(String word: doc){
            if(term.equalsIgnoreCase(word))
                result++;
        }

        return Math.log(1 + result);
    }

    @Override
    public Map<String, Double> getScoredDoc(String url) {
        try{
            TermWeights.clear();
            document = Jsoup.connect(url).get();
            /*
            To ignore wikipedia references
             */
            title = document.title();
            document.select("ol.references").remove();
            calculateScore(document, "html");
            calculateScore(document,"head");
            calculateScore(document, "title");
            calculateScore(document, "meta");
            calculateScore(document, "body");
            calculateScore(document, "h1");
            calculateScore(document, "h2");
            calculateScore(document, "h3");
            calculateScore(document, "h4");
            calculateScore(document, "h5");
            calculateScore(document, "h6");
            calculateScore(document, "code");
            calculateScore(document, "address");
            calculateScore(document, "summary");
            calculateScore(document, "blockquote");
            calculateScore(document, "mark");
            calculateScore(document, "ins");
            calculateScore(document, "map");
            calculateScore(document, "p");
            calculateScore(document, "span");
            calculateScore(document, "div");
            calculateScore(document, "ul");
            calculateScore(document, "ol");
            calculateScore(document, "li");
            calculateScore(document, "article");
            calculateScore(document, "nav");
            TermWeights = sortByValues(TermWeights);
            return TermWeights;
        }catch (IOException e){
            LOGGER.info(e.getMessage());
        }
        return TermWeights;
    }

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return document.select("table.infobox").text();
    }

    @Override
    public void readJSON() {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("/knowledge-vault/web-scraping/scoringscheme.json")){
            Object obj = jsonParser.parse(reader);
            JSONArray jsonArray = new JSONArray();
            jsonArray.add(obj);
            jsonArray.forEach( score -> parseScore( (JSONObject) score ) );


        }catch (FileNotFoundException e){
            LOGGER.info(e.getMessage());
        }catch (IOException e){
            LOGGER.info(e.getMessage());
        }catch (ParseException e){
            LOGGER.info(e.getMessage());
        }
    }

    private void parseScore(JSONObject score)
    {
        //Get employee object within list
        String tagName = (String) score.get("title");
        Long value = (Long) score.get("child.weight");
        tagWeights.put(tagName, value);

        JSONArray childObject = (JSONArray) score.get("child");

        for(int i=0;i<childObject.size();i++){
            JSONObject jsonObject = (JSONObject) childObject.get(i);
            String tag = (String) jsonObject.get("title");
            Long values = (Long) jsonObject.get("child.weight");
            tagWeights.put(tag, values);

            JSONArray innerscores = (JSONArray) jsonObject.get("child");

            for(int j=0;j<innerscores.size();j++){
                JSONObject jsonObject1 = (JSONObject) innerscores.get(j);
                String tags = (String) jsonObject1.get("title");
                Long value1 = (Long) jsonObject1.get("child.weight");
                tagWeights.put(tags, value1);
            }
        }
    }
    /*
    Helper function to sort the terms by their tfidf value.
     */

    private static HashMap sortByValues(HashMap map) {

        List list = new LinkedList(map.entrySet());
        // Defined Custom Comparator here
        list.sort((Object o1, Object o2)->(((Comparable)(((Map.Entry)o2).getValue())).compareTo((((Map.Entry)o1).getValue()))));

        // Here I am copying the sorted list in HashMap
        // using LinkedHashMap to preserve the insertion order
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }
}
