package com.stackroute.knowledgevault.documentidentifier.services;


import com.stackroute.knowledgevault.domain.ExtractedFileData;
import com.stackroute.knowledgevault.domain.JsonLDObject;
import com.stackroute.knowledgevault.domain.OutputForDoc;
import com.stackroute.knowledgevault.documentidentifier.repository.DocumentRepository;
import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;
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
        int numberOfWords = 40;

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

            if(!term.isEmpty() && term.length()>4) {
                Sentence sentence = new Sentence(term);
                if(sentence.posTag(0).equals("NN")) {
                    relevantWords.add(term);
                    i++;
                }
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

    /*
    Function that tokenizes, lemmatizes words in all the documents. Furthermore, removes the stopwords in the docs and returns a
    list of "OutputForDoc" class containing id, title and relevant terms in the doc.
     */
    @Override
    public List<OutputForDoc> processDoc(List<ExtractedFileData> extractedFileData, int n) {
        List<List<String>> docs = new ArrayList<>();
        List<OutputForDoc> relevantTerms = new ArrayList<>();

        for(ExtractedFileData extractedFileData1: extractedFileData){
            List<String> terms = getnGrams(extractedFileData1, n);
            docs.add(terms);
        }

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

    @Override
    public List<Document> convertStringToDocument(List<ExtractedFileData> extractedFileData) {
        List<Document> documents = new ArrayList<>();
        for(ExtractedFileData extractedFile : extractedFileData){
            documents.add(new Document(extractedFile.getContent()));
        }
        return documents;
    }

    /*
    Helper function to retrieve NGrams of the document "extractedFileData". "n" specifies the size of the phrase
     */

    public List<String> getnGrams(ExtractedFileData extractedFileData, int n){
        String content = extractedFileData.getContent().replaceAll("[^\\w\\s\\ ]", "").toLowerCase();
        NGram nGram = new NGram(content,n);
        return nGram.list();
    }

    /*
    Function that converts List of "OutputForDoc" to "JsonLDObject" based on the following schema:
        {
            "differentialDiagnosis": {
                "distinguishingSign": [
                    {
                        "@type": "MedicalSymptom",
                        "name": "inflammation of meninges",
                        "symptomkeyword": "inflammation of"
                    },
                    {
                        "@type": "MedicalSymptom",
                        "name": "inflammation",
                        "symptomkeyword": "inflammation of"
                    }
                ]
            },
            "@type": "MedicalCondition",
            "associatedAnatomy": {},
            "alternateName": "acute inflammation of lacrimal passage",
            "keyword": "inflammation of",
            "@context": "http://schema.org"
        }
     */

    @Override
    public List<JsonLDObject> getJsonLD(List<OutputForDoc> outputForDocs) {

        GetDiseasesAndSymptoms getDiseasesAndSymptoms = new GetDiseasesAndSymptoms();
        List<String> diseases = getDiseasesAndSymptoms.getDiseases();
        List<String> symptoms = getDiseasesAndSymptoms.getSymptoms();
        List<String> bodyparts = getDiseasesAndSymptoms.getBodyParts();
        List<JsonLDObject> jsonLDObjects = new ArrayList<>();

        for(OutputForDoc documents: outputForDocs){
          boolean foundDisease = false;
            for(String keyword: documents.getKeywords()){
                Map<String, Object> root = new HashMap<>();


                for(String disease: diseases){
                    if((disease.contains(keyword) || keyword.contains(disease)) && !disease.isEmpty()){
                        root.put("alternateName", disease);
                        root.put("keyword", keyword);
                        foundDisease = true;
                        break;
                    }
                }
                if(foundDisease){
                    root.put("@context","http://schema.org");
                    root.put("@type", "MedicalCondition");
                    foundDisease = false;
                }else{
                    continue;
                }
                Map<String, String> Anatomy = new HashMap<>();
                for(String bodypart: bodyparts){
                    if((bodypart.contains(keyword) || keyword.contains(bodypart)) && !bodypart.isEmpty()){
                        Anatomy.put("@type", "AnatomicalStructure");
                        Anatomy.put("name", bodypart);
                        Anatomy.put("anatomykeyword", keyword);
                    }
                }
                Map<String, Object> Symptoms = new HashMap<>();
                List<Map<String, Object>> diagnosis = new ArrayList<>();
                for(String symptom: symptoms){
                    Map<String, Object> tempMap = new HashMap<>();
                    if((symptom.contains(keyword) || keyword.contains(symptom)) && !symptom.isEmpty()){
                        tempMap.put("@type","MedicalSymptom");
                        tempMap.put("name", symptom);
                        tempMap.put("symptomkeyword", keyword);
                        diagnosis.add(tempMap);
                    }
                }
                Symptoms.put("distinguishingSign", diagnosis);

                root.put("associatedAnatomy", Anatomy);
                root.put("differentialDiagnosis", Symptoms);
                jsonLDObjects.add(new JsonLDObject(documents.getId(), root));

            }
        }
        return jsonLDObjects;
    }


}
