package com.stackroute.knowledgevault.paragraphprocessor.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.knowledgevault.domain.JSONld;
import com.stackroute.knowledgevault.paragraphprocessor.MyAnalyzer;
import com.stackroute.knowledgevault.paragraphprocessor.algos.NGramTfIdf;
import com.stackroute.knowledgevault.paragraphprocessor.algos.POSTagging;
import com.stackroute.knowledgevault.paragraphprocessor.algos.TfIdf;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.Version;
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
        Map<String,String>  posTagged = POSTagging.tagger(paragraph," ");
        List<String> keywords = new ArrayList<>();
        for(Map.Entry<String,String> entry: posTagged.entrySet()) {
            String pos = entry.getValue();
            if(pos.contains("NN") || pos.contains("VB")) {
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


        Collections.sort(list,(o1,o2) ->
                ((Comparable) ((Map.Entry) (o2)).getValue()).compareTo(((Map.Entry) (o1)).getValue())
        );

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

        List<Integer> ns = Arrays.asList(1,2);

        TfIdf.Normalization normalization = TfIdf.Normalization.NONE;
        boolean smooth = false;
        boolean noAddOne = false;

        List<String> text = Arrays.asList(paragraph.trim().split("\\n+"));

        Iterable<Collection<String>> documents = NGramTfIdf.ngramDocumentTerms(ns, text);

        Iterable<Map<String, Double>> tfs = TfIdf.tfs(documents);
        Map<String, Double> idf = TfIdf.idfFromTfs(tfs, smooth, !noAddOne);
        LOGGER.info("TF-IDF scores: ");
        Map<String,Double> res = new HashMap<>();
        for (Map<String, Double> tf : tfs) {
            Map<String, Double> tfIdf = TfIdf.tfIdf(tf, idf, normalization);
            Map<String,Double> tmp = sortByValues(tfIdf);
            int i=0;
            for(Map.Entry<String,Double> entry : tmp.entrySet()) {
                if(i<Math.min(50,tmp.size())) {
                    res.put(entry.getKey(),entry.getValue());
                    i++;
                }
            }
        }
        return res;
    }

    /**
     * This function lemmetizes a keyword by using lucene.
     * @param key: the keyword to be lemmmetized
     * @return: the String containing lemma of the keyword.
     */
    public static String luceneLemmetize(String key) {
        StringBuilder sb = new StringBuilder();
        Directory dir = new RAMDirectory();
        Analyzer analyzer = new MyAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(Version.LATEST,analyzer);
        try {
            IndexWriter wr = new IndexWriter(dir,config);
            org.apache.lucene.document.Document doc = new org.apache.lucene.document.Document();
            doc.add(new Field("content",key, Field.Store.YES, Field.Index.ANALYZED));
            wr.addDocument(doc);
            wr.close();
            IndexReader iReader = IndexReader.open(dir);
            Fields fields = MultiFields.getFields(iReader);
            Iterator<String> iterator = fields.iterator();

            while (iterator.hasNext()) {
                String field = iterator.next();
                Terms terms = MultiFields.getTerms(iReader, field);
                TermsEnum it = terms.iterator(null);
                BytesRef term = it.next();
                while (term != null) {
                    String data = term.utf8ToString();
                    sb.append(data+" ");
                    term = it.next();
                }
            }
            dir.close();
        }
        catch (IOException e) {
            LOGGER.info(e.getMessage());
        }
        return sb.toString();
    }

    /**
     * This function checks if a keyword is valid while being searched during tagging phase in a dictionary.
     * @param key : the keyword that is to be tagged
     * @param txt : the word that the keywrod is being compared to for a match in the dictionary.
     * @return : true if a match is found , otherwise false.
     */
    public static boolean validKey(String key,String txt)  {
        boolean status = false;
        String l1 = luceneLemmetize(txt.trim());
        String l2 = luceneLemmetize(key.trim());
        if(l1.compareToIgnoreCase(l2)==0 || l2.compareToIgnoreCase(txt.trim())==0 ||
                l1.compareToIgnoreCase(key.trim())==0 || txt.trim().compareToIgnoreCase(key.trim())==0) status = true;
        return status;
    }

    /**
     * This function creates tags for a list of keywords by  doing fast-lookup in a bunch of appropriate dictionaries
     * and returns a tagged Map<tag ,List< <Disease,Score> > >.
     * @param : key value pairs of keywords and their score in a given document.
     * @return : Map<tag ,List< <Disease,Score> > > where each tag has a list of disease with therir scores attached
     * to indicate their degree of relevance/importance in the document the keywords were extracted from.
     */

    public static Map<String,List<Pair>> generateTags(Map<String,Double> keys) {

        Map<String,List<Pair>> tags = new HashMap<>();
        for(Map.Entry<String,Double> key: keys.entrySet()) {

            File dictionary = new File("../../knowledge-vault/paragraph-processor/assets/taggerResource/");
            for(File f: dictionary.listFiles()) {

                String filename = f.getName();
                tags.putIfAbsent(filename,new ArrayList());

                try(BufferedReader br = new BufferedReader(new FileReader(f))) {
                    String txt;
                    while((txt = br.readLine())!=null) {
                        if (txt.length() > 0) {
                            if (txt.trim().compareToIgnoreCase(key.getKey().trim()) == 0 || key.getKey().trim().compareToIgnoreCase(txt.trim()) == 0) {
                                Pair p = new Pair(txt.trim(), key.getValue());
                                tags.get(f.getName()).add(p);
                                break;
                            }
                        }
                    }
                }
                catch (IOException e) {
                    LOGGER.error("error found: {} ", e.getStackTrace());
                }
                catch(Exception e) {
                    LOGGER.error(e.getMessage());
                }
            }
        }

        LOGGER.info("generated tags: {}",tags);
        return tags;
    }

    /**
     * Convert data from JSON object to JSONld object by using Object Mapper class in java
     * @param obj : JSON object
     * @param docId :  the id of the document that the paragraph belongs to.
     * @param: paraId: the id of the paragraph itself
     * @return : JSONld object containing the data to be used by Populator micro-service to populate the knowledge graph.
     */

    public static JSONld json2jsonld(JSONObject obj,int docId,int paraId) {
        ObjectMapper mapper = new ObjectMapper();
        JSONld jsoNld = null;
        try {
            HashMap<String,Object> mapData = mapper.readValue(obj.toString(), HashMap.class);
            jsoNld = new JSONld(docId,paraId,mapData);
        }
        catch (IOException e) {
            LOGGER.error("error found: {} ", e.getStackTrace());
        }
        catch (Exception e) {
		    LOGGER.error("error found here: {}", e.getStackTrace());
	    }
        return jsoNld;
    }


}
