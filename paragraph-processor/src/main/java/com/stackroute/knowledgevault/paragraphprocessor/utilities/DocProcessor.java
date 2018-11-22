package com.stackroute.knowledgevault.paragraphprocessor.utilities;

import com.stackroute.knowledgevault.paragraphprocessor.algos.NGramTfIdf;
import com.stackroute.knowledgevault.paragraphprocessor.algos.POSTagging;
import com.stackroute.knowledgevault.paragraphprocessor.algos.TfIdf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static java.util.stream.Collectors.toMap;

public class DocProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocProcessor.class);

    public static List<String> getValidKeys(String paragraph) {
        Map<String,String>  pos_tagged = POSTagging.tagger(paragraph);
        List<String> keywords = new ArrayList<>();
        for(Map.Entry<String,String> entry: pos_tagged.entrySet()) {
            String pos = entry.getValue();
            if(pos.compareTo("NN")==0 || pos.compareTo("NNS")==0 || pos.compareTo("JJ")==0) {
                keywords.add(entry.getKey());
            }
        }
        return keywords;
    }

    public static HashMap sortByValues(Map map) {

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

    public static Map<String,Double> performNGramAnalysis(String paragraph)  {

        NGramTfIdf nGramTfIdf = new NGramTfIdf();
        List<Integer> ns = new ArrayList(){{
            for(int i=0;i<3;i++) add(i+1);
        }};

        TfIdf.Normalization normalization = TfIdf.Normalization.COSINE;
        boolean smooth = false;
        boolean noAddOne = false;

        List<String> text = Arrays.asList(paragraph.trim().split("\\.|\\n"));

        Iterable<Collection<String>> documents = nGramTfIdf.ngramDocumentTerms(ns, text);

        Iterable<Map<String, Double>> tfs = TfIdf.tfs(documents);
        Map<String, Double> idf = TfIdf.idfFromTfs(tfs, smooth, !noAddOne);
        LOGGER.info("TF-IDF scores: ");
        Map<String,Double> res = new HashMap<>();
        for (Map<String, Double> tf : tfs) {
            Map<String, Double> tfIdf = TfIdf.tfIdf(tf, idf, normalization);
            Map<String,Double> tmp = DocProcessor.sortByValues(tfIdf);
//            LOGGER.info(tmp.toString());
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



}
