package com.stackroute.knowledgevault.documentidentifier.services;


import com.stackroute.knowledgevault.domain.ExtractedFileData;
import com.stackroute.knowledgevault.domain.JsonLDObject;
import com.stackroute.knowledgevault.domain.OutputForDoc;
import com.stackroute.knowledgevault.documentidentifier.repository.DocumentRepository;
//import edu.stanford.nlp.simple.Document;
//import edu.stanford.nlp.simple.Sentence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Implementation class of the interface DocumentService
 */

@Service
@Primary
public class DocumentServiceImpl implements DocumentService {



    @Autowired
    private DocumentRepository documentRepository;

    /*
    Function to save documents consumed by the extractor service. Uses DocumentRepository to save to mongodb.
     */

    @Override
    public ExtractedFileData saveDocuments(ExtractedFileData extractedFileData) {
        return documentRepository.save(extractedFileData);
    }

    /*
    Retrieve all documents from mongodb. uses DocumentRepository to retrieve files.
     */

    @Override
    public List<ExtractedFileData> getAllDocuments() {
        return documentRepository.findAll();
    }

    /*
    Function to calculate term frequency(tf) score of a term in a document. Takes all the terms in a doc and term to be calculated.
    Returns normalized tf score, i.e. dividing the term frequency with the total number of terms to keep the score < 1.
     */

    @Override
    public double tf(List<String> doc, String term){
        double result = 0;
        for(String word: doc){
            if(term.equalsIgnoreCase(word))
                result++;
        }

        return Math.log(1 + result);
    }

    /*
    Function to calculate the inverse document frequency (idf) score of a term from all the documents. Takes all the terms in all the docs to calculate
    the score. Returns the natural logarithm of number of docs by number of occurrences of a term.
     */
    @Override
    public double idf(List<List<String>> docs, String term) {
        double n = 0;
        for (List<String> doc : docs) {
            for (String word : doc) {
                if (term.equalsIgnoreCase(word)) {
                    n++;
                    break;
                }
            }
        }
        return Math.log((docs.size()) / (n+1));
    }

    /*
    Calculates the tfidf score of each term in a document. Calls the above two functions to perform that.
    Furthermore, the function sorts the terms in the document based on tfidf score and returns the top 20 terms.
     */

    @Override
    public List<String> tfIdf(int index, List<List<String>> docs) {

        LinkedHashMap<String, Double> weightMap = new LinkedHashMap<>();
        List<String> relevantWords = new ArrayList<>();
        int numberOfWords = 20;

        double denominator = 0.0;

        for(String words: docs.get(index)){
            double relevance = tf(docs.get(index), words) * idf(docs, words);
            denominator += (relevance * relevance);
            weightMap.put(words, relevance);


        }
        denominator = Math.sqrt(denominator);
        Iterator it = weightMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            weightMap.put((String)pair.getKey(),((double)pair.getValue())/denominator);
        }
        Map<String, Double> sortedMap = sortByValues(weightMap);

        Iterator<Map.Entry<String, Double>> itr = sortedMap.entrySet().iterator();
        int i=0;
        while(itr.hasNext() && i<=numberOfWords){
            String term = itr.next().getKey().trim();
            if(!term.isEmpty()) {
                relevantWords.add(term);
                i++;
            }
        }
        return relevantWords;
    }

    /*
    Helper function to sort the terms by their tfidf value.
     */

    private static HashMap sortByValues(HashMap map) {

        List list = new LinkedList(map.entrySet());
        // Defined Custom Comparator here
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o2)).getValue())
                        .compareTo(((Map.Entry) (o1)).getValue());
            }
        });

        // Here I am copying the sorted list in HashMap
        // using LinkedHashMap to preserve the insertion order
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }

    /*
    Function that tokenizes, lemmatizes words in all the documents. Furthermore, removes the stopwords in the docs and returns a
    list of "OutputForDoc" class containing id, title and relevant terms in the doc.
     */
    @Override
    public List<OutputForDoc> processDoc(List<ExtractedFileData> extractedFileData, int n) {
        List<List<String>> docs = new ArrayList<>();
//        StopwordRemoval stopWordRemoval = new StopwordRemoval();
//        List<Document> documents = convertStringToDocument(extractedFileData);
        List<OutputForDoc> relevantTerms = new ArrayList<>();

        for(ExtractedFileData extractedFileData1: extractedFileData){
            List<String> terms = getnGrams(extractedFileData1, n);
            docs.add(terms);
        }

//        for(Document document: documents){
//            List<String> terms = new ArrayList<>();
//            for(Sentence sentence: document.sentences()){
//                for(int i=0;i<sentence.length();i++){
//                    terms.add(sentence.lemma(i).toLowerCase());
//                }
//            }
//            terms = stopWordRemoval.removeStopwords(terms);
//            docs.add(terms);
//        }
        for(int i = 0; i< extractedFileData.size(); i++){
            relevantTerms.add(new OutputForDoc(extractedFileData.get(i).getId(),
                    extractedFileData.get(i).getMetadata(),
                    tfIdf(i, docs)));
        }

        return relevantTerms;

    }

    /*
    Helper function to convert List of "ExtractedFileData" to "Document" defined in Stanford Simple NLP
     */

//    @Override
//    public List<Document> convertStringToDocument(List<ExtractedFileData> extractedFileData) {
//        List<Document> documents = new ArrayList<>();
//        for(ExtractedFileData extractedFile : extractedFileData){
//            documents.add(new Document(extractedFile.getContent()));
//        }
//        return documents;
//    }

    public List<String> getnGrams(ExtractedFileData extractedFileData, int n){
        String content = extractedFileData.getContent().replaceAll("[^\\w\\s\\ ]", "").toLowerCase();
        NGram nGram = new NGram(content,n);
        return nGram.list();
    }

    @Override
    public List<JsonLDObject> convertTermsToJsonLD(List<OutputForDoc> outputForDocs) {
        /*
            {
            "@context": "http://schema.org",
            "@type": "MedicalCondition",
            "alternateName": "angina pectoris",
            "associatedAnatomy": {
                "@type": "AnatomicalStructure",
                "name": "heart"
                }
            }
         */
        GetDiseasesAndSymptoms getDiseasesAndSymptoms = new GetDiseasesAndSymptoms();
        List<String> diseases = getDiseasesAndSymptoms.getDiseases();
        List<String> bodyparts = getDiseasesAndSymptoms.getBodyParts();
        List<JsonLDObject> jsonLDObjects = new ArrayList<>();
        for(OutputForDoc documents: outputForDocs){
            Map<String, Object> root = new HashMap<>();
            root.put("@context","http://schema.org");
            root.put("@type", "MedicalCondition");
            for(String keyword: documents.getKeywords()){

                for(String disease: diseases){
                    if(disease.contains(keyword) || keyword.contains(disease)){
                        System.out.println(keyword);
                        root.put("alternateName", disease);
                    }
                }
                Map<String, String> Anatomy = new HashMap<>();
                for(String bodypart: bodyparts){
                    if(bodypart.contains(keyword) || keyword.contains(bodypart)){
                        Anatomy.put("@type", "AnatomicalStructure");
                        Anatomy.put("name", bodypart);
                    }
                }

                root.put("associatedAnatomy", Anatomy);

            }
            jsonLDObjects.add(new JsonLDObject(documents.getId(), root));
        }
        return jsonLDObjects;
    }


}
