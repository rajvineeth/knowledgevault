package com.stackroute.services;


import com.stackroute.domain.ExtractedFileData;
import com.stackroute.domain.OutputForDoc;
import com.stackroute.repository.DocumentRepository;
import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Primary
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public List<ExtractedFileData> saveDocuments(List<ExtractedFileData> extractedFileData) {
        return documentRepository.saveAll(extractedFileData);
    }

    @Override
    public List<ExtractedFileData> getAllDocuments() {
        return documentRepository.findAll();
    }

    @Override
    public double tf(List<String> doc, String term) {
        double result = 0;
        for(String word: doc){
            if(term.equalsIgnoreCase(word))
                result++;
        }
        return result / doc.size();
    }

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
        if(n > 0) {
            return Math.log(docs.size() / n);
        }else{
            return 0;
        }
    }

    @Override
    public List<String> tfIdf(int index, List<List<String>> docs) {
        LinkedHashMap<String, Double> weightMap = new LinkedHashMap<>();
        List<String> relevantWords = new ArrayList<>();
        int numberOfWords = 20;
        for(String words: docs.get(index)){
            double relevance = tf(docs.get(index), words) * idf(docs, words);
            weightMap.put(words, relevance);


        }
        Map<String, Double> sortedMap = sortByValues(weightMap);
        Iterator<Map.Entry<String, Double>> itr = sortedMap.entrySet().iterator();
        int i=0;
        while(itr.hasNext() && i<=numberOfWords){
            relevantWords.add(itr.next().getKey());
            i++;
        }
        return relevantWords;
    }

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

    @Override
    public List<OutputForDoc> processDoc(List<ExtractedFileData> extractedFileData) {
        List<List<String>> docs = new ArrayList<>();
        StopwordRemoval stopWordRemoval = new StopwordRemoval();
        List<Document> documents = convertStringToDocument(extractedFileData);
        List<OutputForDoc> relevantTerms = new ArrayList<>();

        for(Document document: documents){
            List<String> terms = new ArrayList<>();
            for(Sentence sentence: document.sentences()){
                for(int i=0;i<sentence.length();i++){
                    terms.add(sentence.lemma(i));
                }
            }
            terms = stopWordRemoval.removeStopwords(terms);
            docs.add(terms);
        }
        for(int i = 0; i< extractedFileData.size(); i++){
            relevantTerms.add(new OutputForDoc(extractedFileData.get(i).getId(),
                    extractedFileData.get(i).getMetadata(),
                    tfIdf(i, docs)));
        }

        return relevantTerms;

    }

    @Override
    public List<Document> convertStringToDocument(List<ExtractedFileData> extractedFileData) {
        List<Document> documents = new ArrayList<>();
        for(ExtractedFileData extractedFile : extractedFileData){
            documents.add(new Document(extractedFile.getContent()));
        }
        return documents;
    }


}
