package com.stackroute.knowledgevault.inputtagger.utils;

import com.stackroute.knowledgevault.domain.InputObject;
import com.stackroute.knowledgevault.domain.Keyword;
import com.stackroute.knowledgevault.domain.ProcessedInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


@Component
public class Processor {

    private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class);
    private static String commonPath = "/knowledge-vault/input-tagger/assets/taggerResource/";
//    private static String commonPath = "../knowledge-vault/input-tagger/assets/taggerResource/";
    private static String personName = commonPath + "person-name";
    private static String disease = commonPath + "diseases";
    private static String symptom = commonPath + "symptom";
    private static String bodyPart = commonPath + "body-parts";

    public ProcessedInput process (InputObject inputObject) {
        LOGGER.info("inside Processor.process()");
        LOGGER.info("Object recieved: {}",inputObject.toString());
        List<String> sentences = makeSentences(inputObject);
        List<String> nGrams = getNGrams(sentences);
        List<Keyword> nGramTokens = produceNGramTokens(nGrams);
        LOGGER.info("The ngrams returned: {}", nGramTokens);
        Map<String, String> keywordMap = new HashMap<>();
        boolean ageFlag = true;

        for (Keyword keyword: nGramTokens) {
            LOGGER.info("inside Processor.process().for(Keyword {}: keywords", keyword);
            LOGGER.info("Keyword POS: {}",keyword.getPos());
            if (keyword.getLemma().equals("")) {
                LOGGER.info(" Null keyword so continuing....");
                continue;
            }
            switch (keyword.getPos()) {

                case "NNP":
                    LOGGER.info("inside Processor.process().for(Keyword {}: keywords case NNP", keyword);
                    if (checkIf(personName, keyword.getLemma())){
                        LOGGER.info("inside Processor.process().for(Keyword {}: keywords case NNP if()", keyword);
                        LOGGER.info("Tagging {} to person name...", keyword.getLemma());
                        keywordMap.put(keyword.getLemma(),"person-name");
                    }
                    break;
                case "CD":
                    LOGGER.info("inside Processor.process().for(Keyword {}: keywords case CD", keyword);
                    if (ageFlag && Integer.valueOf(keyword.getLemma()) > 0 && Integer.valueOf(keyword.getLemma()) <= 100){
                        LOGGER.info("inside Processor.process().for(Keyword {}: keywords case CD if()", keyword);
                        LOGGER.info("Tagging {} to age...", keyword.getLemma());
                        keywordMap.put(keyword.getLemma(), "age");
                        ageFlag = false;
                    }
                    break;
                case "NNS":
                    LOGGER.info("inside Processor.process().for(Keyword {}: keywords case NNS", keyword);
                    if (checkIf(disease, keyword.getLemma())) {
                        LOGGER.info("inside Processor.process().for(Keyword {}: keywords case NNS if()", keyword);
                        LOGGER.info("Tagging {} to disease...",keyword.getLemma());
                        keywordMap.put(keyword.getLemma(), "MedicalCondition");
                        break;
                    }
                    else if (checkIf(symptom, keyword.getLemma())) {
                        LOGGER.info("Tagging {} to symptom...",keyword.getLemma());
                        keywordMap.put(keyword.getLemma(), "MedicalSymptom");
                        break;
                    }
                    else if (checkIf(bodyPart, keyword.getLemma())) {
                        LOGGER.info("Tagging {} to body-part...",keyword.getLemma());
                        keywordMap.put(keyword.getLemma(), "AnatomicalStructure");
                        break;
                    }
                    else if (checkIf(personName, keyword.getLemma())){
                        LOGGER.info("inside Processor.process().for(Keyword {}: keywords case NNP if()", keyword);
                        LOGGER.info("Tagging {} to person name...", keyword.getLemma());
                        keywordMap.put(keyword.getLemma(),"person-name");
                    }
                    else continue;
                case "NN":
                    LOGGER.info("inside Processor.process().for(Keyword {}: keywords case NN", keyword);
                    if (checkIf(disease, keyword.getLemma())) {
                        LOGGER.info("inside Processor.process().for(Keyword {}: keywords case NN if()", keyword);
                        LOGGER.info("Tagging {} to disease...",keyword.getLemma());
                        keywordMap.put(keyword.getLemma(), "MedicalCondition");
                        break;
                    }
                    else if (checkIf(symptom, keyword.getLemma())) {
                        LOGGER.info("inside Processor.process().for(Keyword {}: keywords case NN else if()", keyword);
                        LOGGER.info("Tagging {} to symptom...",keyword.getLemma());
                        keywordMap.put(keyword.getLemma(), "MedicalSymptom");
                        break;
                    }
                    else if (checkIf(bodyPart, keyword.getLemma())) {
                        LOGGER.info("inside Processor.process().for(Keyword {}: keywords case NN else if()2", keyword);
                        LOGGER.info("Tagging {} to body-part...",keyword.getLemma());
                        keywordMap.put(keyword.getLemma(), "AnatomicalStructure");
                        break;
                    }
                    else if (checkIf(personName, keyword.getLemma())){
                        LOGGER.info("inside Processor.process().for(Keyword {}: keywords case NNP if()", keyword);
                        LOGGER.info("Tagging {} to person name...", keyword.getLemma());
                        keywordMap.put(keyword.getLemma(),"person-name");
                    }
                    else continue;
                default:
                    LOGGER.info("inside Processor.process().for(Keyword {}: keywords default", keyword);
            }
        }
        LOGGER.info("the created intermediate keword map: {}", keywordMap);
        if (keywordMap.isEmpty()) {
            LOGGER.info("keywordMap is empty!!");
            Map<String,String> map = new HashMap<>();
            map.put("empty", "empty");
            return new ProcessedInput(map);
        }
        List<String> keyList = new ArrayList();
        LOGGER.info("Initialized a new keylist.");
        for (String s: keywordMap.keySet()) {
            LOGGER.info("Adding keyword {} to the list", s);
            keyList.add(s);
        }
        LOGGER.info("List of keys: {}", keyList);
        for (int j = 0; j < keyList.size(); j++) {
            LOGGER.info("Checking substrings in {}", keyList.get(j));
            for (int k = j+1; k < keyList.size(); k++) {
                LOGGER.info("Searching for {} in {}", keyList.get(k), keyList.get(j));
                if (keyList.get(j).contains(keyList.get(k))) {
                    LOGGER.info("{} is substring so removing {}", keyList.get(k), keyList.get(k));
                    keyList.remove(k--);
                }
            }
        }
        LOGGER.info("Final list of keys: {}", keyList);
        Map finalMap = new HashMap();
        for (Map.Entry<String, String> entry: keywordMap.entrySet()) {
            if (keyList.contains(entry.getKey())) {
                LOGGER.info("Adding {}", entry.getKey());
                finalMap.put(entry.getKey(),entry.getValue());
            }
        }
        LOGGER.info("the created final keword map: {}", finalMap);
        return new ProcessedInput(finalMap);
    }

    private List<String> getNGrams(List<String> sentences) {
        List<String> ngrams = new ArrayList<>();
        int length;
        for (String s: sentences) {
            length = s.trim().split(" ").length;
            if (length > 6) {
                    ngrams.addAll(nGramForSentence(s, 6));
            }
            else {
                    ngrams.addAll(nGramForSentence(s, length));
            }
        }
        LOGGER.info("returning from getNgrams() \" {} \"", ngrams);

        return ngrams;
    }

    private List<String> nGramForSentence(String s, int i) {
        List<String> ngrams = new ArrayList<>();
        while (i > 0) {
            ngrams.addAll(new NGram(s.trim(), i--).list());
        }
        LOGGER.info("for {} ngrams are {}", s, i, ngrams);
        return  ngrams;
    }

    private List<String> makeSentences(InputObject inputObject) {
        List<String> sentenceList = new ArrayList<>();
        String sentence = "";
        for (String s: inputObject.getLemmas()) {
            LOGGER.info("For lemma: {}", s);
            sentence = sentence + " " + s;
            LOGGER.info("now sentence is \" {} \"", sentence);
        }
        if (sentence.contains(".")) {
            LOGGER.info("sentence contains a full stop");
            for (String s: sentence.split("\\.")) {
                LOGGER.info("Adding {} to list", s);
                sentenceList.add(s);
            }
        }
        else {
            LOGGER.info("sentence doesnt have full stop");
            sentenceList.add(sentence);
        }
        LOGGER.info("returning following sentences {}", sentenceList);
        return  sentenceList;
    }

    private List<Keyword> produceNGramTokens(List<String> inputObject) {
        LOGGER.info("produceGram() gets Input Object: {}", inputObject);
        List<Keyword> keywords = new ArrayList<>();
        int sentenceID = 0;
        for (String s: inputObject) {
            keywords.add(new Keyword(1,++sentenceID, s, "NN" ));
        }
        LOGGER.info("Returning Keywords: {}", keywords);
        return keywords;
    }

    private boolean checkIf(String path, String lemma) {
        File dictionary = new File(path);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(dictionary))) {
            String txt;
            while ((txt = bufferedReader.readLine()) != null) {
                if (txt.compareToIgnoreCase(lemma) == 0) {
                    return true;
                }
            }
        } catch (IOException e) {
            LOGGER.info("context {}",e);
        }
        return false;
    }
}
