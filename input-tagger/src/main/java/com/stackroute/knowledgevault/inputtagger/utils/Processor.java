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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class Processor {

    private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class);
    private static String commonPath = "./input-tagger/assets/taggerResource/";
    private static String personName = commonPath + "person-name";
    private static String disease = commonPath + "diseases";
    private static String symptom = commonPath + "symptom";
    private static String bodyPart = commonPath + "body-parts";

    public ProcessedInput process (InputObject inputObject) {
        LOGGER.info("inside Processor.process()");
        LOGGER.info("********InputObject*******");
        LOGGER.info("Inside Processor.process() method");
        LOGGER.info("{}",inputObject.toString());
        List<Keyword> keywords = KeywordFilter.filterKeywords(inputObject);
        List<Keyword> nGramTokens = produceNGram(inputObject);
        for (Keyword k: nGramTokens) {
            keywords.add(k);
        }
        Map<String, String> keywordMap = new HashMap<>();
        boolean ageFlag = true;

        for (Keyword keyword: keywords) {
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
                        keywordMap.put(keyword.getLemma(), "disease");
                        break;
                    }
                    else if (checkIf(symptom, keyword.getLemma())) {
                        LOGGER.info("Tagging {} to symptom...",keyword.getLemma());
                        keywordMap.put(keyword.getLemma(), "symptom");
                        break;
                    }
                    else if (checkIf(bodyPart, keyword.getLemma())) {
                        LOGGER.info("Tagging {} to body-part...",keyword.getLemma());
                        keywordMap.put(keyword.getLemma(), "body-part");
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
                        keywordMap.put(keyword.getLemma(), "disease");
                        break;
                    }
                    else if (checkIf(symptom, keyword.getLemma())) {
                        LOGGER.info("inside Processor.process().for(Keyword {}: keywords case NN else if()", keyword);
                        LOGGER.info("Tagging {} to symptom...",keyword.getLemma());
                        keywordMap.put(keyword.getLemma(), "symptom");
                        break;
                    }
                    else if (checkIf(bodyPart, keyword.getLemma())) {
                        LOGGER.info("inside Processor.process().for(Keyword {}: keywords case NN else if()2", keyword);
                        LOGGER.info("Tagging {} to body-part...",keyword.getLemma());
                        keywordMap.put(keyword.getLemma(), "body-part");
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
        LOGGER.info("\nthe created final keword map:\n {}", keywordMap);
        if (keywordMap.isEmpty()) {
            Map<String,String> map = new HashMap<>();
            map.put("empty", "empty");
            return new ProcessedInput(map);
        }
        return new ProcessedInput(keywordMap);
    }

    private List<Keyword> produceNGram(InputObject inputObject) {
        LOGGER.info("inside Processor.produceNGram()...");
        List<Keyword> keywords = new ArrayList<>();
        String sentence;
        int sentenceID = 0;
        List<String> ngrams;
        for (int j = 2; j <= 5; j++){
            LOGGER.info("inside Processor.produceNGram for()...");
            sentenceID = 0;
            for (int i = 0; i < inputObject.getLemmas().size(); i++) {
                LOGGER.info("inside Processor.produceNGram for() for()...");
                sentence = "";
                sentenceID++;
                while (!(inputObject.getLemmas().get(i).equals(".")) && i < inputObject.getLemmas().size()-1){
                    LOGGER.info("inside Processor.produceNGram for() for() while()...");
                    sentence += " " + inputObject.getLemmas().get(i++);
                }
                ngrams = new NGram(sentence, j).list();
                for (String s: ngrams){
                    LOGGER.info("inside Processor.produceNGram for() for() for()...");
                    keywords.add(new Keyword(inputObject.getId(),sentenceID, s, "NN" ));
                }
            }
        }
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