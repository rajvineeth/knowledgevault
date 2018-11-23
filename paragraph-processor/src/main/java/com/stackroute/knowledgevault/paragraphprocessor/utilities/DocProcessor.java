package com.stackroute.knowledgevault.paragraphprocessor.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.knowledgevault.domain.JSONld;
import com.stackroute.knowledgevault.paragraphprocessor.algos.NGramTfIdf;
import com.stackroute.knowledgevault.paragraphprocessor.algos.POSTagging;
import com.stackroute.knowledgevault.paragraphprocessor.algos.TfIdf;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * This Utility class contains methods to process a document
 */
public class DocProcessor {

    private DocProcessor() {}

    private static final Logger LOGGER = LoggerFactory.getLogger(DocProcessor.class);

    /**
     * This function filters unnecessary keywords to narrow down the search space.We don't need to search for pronouns,
     * adjectives,prepositions, etc for named entity Recognition.We first assign tags to the keywords by using stanford NLP pipeline.
     * And then we criss-cross the undesired ones.
     * @param paragraph: the String  you want to work on to get only "Valid" keywords
     * @return:  List of  valid Part-Of-Speech tagged keywords
     */
    public static List<String> getValidKeys(String paragraph) {
        Map<String,String>  posTagged = POSTagging.tagger(paragraph);
        List<String> keywords = new ArrayList<>();
        for(Map.Entry<String,String> entry: posTagged.entrySet()) {
            String pos = entry.getValue();
            if(pos.compareTo("NN")==0 || pos.compareTo("NNS")==0 || pos.compareTo("JJ")==0) {
                keywords.add(entry.getKey());
            }
        }
        return keywords;
    }

    /**
     * This function sorts a Map according to its value field in descending order.
     * @param map: the input Map
     * @return: HashMap object which has same data as the input but with order is changed now with its value field in decreasing order.
     */
    public static Map sortByValues(Map map) {

        List list = new LinkedList(map.entrySet());

        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o2)).getValue())
                        .compareTo(((Map.Entry) (o1)).getValue());
            }
        });

        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }

    /**
     * This function performs N-Gram analysis on a String.We begin with setting how many N-grams we want to deal with.
     * For example,if a combination of 5 words makes sense while individual words don't,then we might wan't to set N-grams to 5.
     * If N=3,then we will be able to make sense of "United States Of America" if  we encounter this in a text as these
     * individual words don't make much sense.
     * @param paragraph: inout String paragraph
     * @return: Map of {key,value} pairs where key is the keyword and value is the score of the keyword.
     */
    public static Map<String,Double> performNGramAnalysis(String paragraph)  {

        List<Integer> ns = new ArrayList(){{
            for(int i=0;i<3;i++) add(i+1);
        }};

        TfIdf.Normalization normalization = TfIdf.Normalization.COSINE;
        boolean smooth = false;
        boolean noAddOne = false;

        List<String> text = Arrays.asList(paragraph.trim().split("\\.|\\n"));

        Iterable<Collection<String>> documents = NGramTfIdf.ngramDocumentTerms(ns, text);

        Iterable<Map<String, Double>> tfs = TfIdf.tfs(documents);
        Map<String, Double> idf = TfIdf.idfFromTfs(tfs, smooth, !noAddOne);
        LOGGER.info("TF-IDF scores: ");
        Map<String,Double> res = new HashMap<>();
        for (Map<String, Double> tf : tfs) {
            Map<String, Double> tfIdf = TfIdf.tfIdf(tf, idf, normalization);
            Map<String,Double> tmp = DocProcessor.sortByValues(tfIdf);
            int i=0;
            for(Map.Entry<String,Double> entry : tmp.entrySet()) {
                if(i<Math.min(20,tmp.size())) {
                    res.put(entry.getKey(),entry.getValue());
                    i++;
                }
            }
        }
        return res;
    }

    /**
     * This function creates tags for a list of keywords by  doing fast-lookup in a bunch of appropriate dictionaries
     * and returns a tagged Map<tag ,List< <Disease,Score> > >.
     * @param : key value pairs of keywords and their score in a given document.
     * @return : Map<tag ,List< <Disease,Score> > > where each tag has a list of disease with therir scores attached
     * to indicate their degree of relevance/importance in the dicument the keywords were extracted from.
     */

    public static Map<String,List<Pair>> generateTags(Map<String,Double> keys) {
        Map<String,List<Pair>> tags = new HashMap<>();
        for(Map.Entry<String,Double> key: keys.entrySet()) {

            File dictionary = new File("../../knowledge-vault/paragraph-processor/assets/taggerResource/");
            for(File f: dictionary.listFiles()) {
                tags.putIfAbsent(f.getName(),new ArrayList<>());
                try(BufferedReader br = new BufferedReader(new FileReader(f))) {
                    String txt;
                    while((txt = br.readLine())!=null) {
                        if(txt.compareTo(key.getKey())==0) {
                            tags.get(f.getName()).add(new Pair(key.getKey(),key.getValue()));
                        }
                    }
                }
                catch (IOException e) {
                    LOGGER.error("error found: {} ", e.getStackTrace());
                }
            }
        }
        LOGGER.info("generated tags: {}",tags);
        return tags;
    }

    public static JSONld json2jsonld(JSONObject  obj,int id) {
        ObjectMapper mapper = new ObjectMapper();
        JSONld jsoNld = null;
        try {
            HashMap<String,Object> mapData = mapper.readValue(obj.toString(), HashMap.class);
            jsoNld = new JSONld(id,mapData);
        } catch (IOException e) {
            LOGGER.error("error found: {} ", e.getStackTrace());
        }
        return jsoNld;
    }
}
