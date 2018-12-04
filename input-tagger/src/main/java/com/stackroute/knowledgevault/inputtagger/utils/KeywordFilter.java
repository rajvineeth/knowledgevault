package com.stackroute.knowledgevault.inputtagger.utils;

import com.stackroute.knowledgevault.domain.InputObject;
import com.stackroute.knowledgevault.domain.Keyword;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class KeywordFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(KeywordFilter.class);

    private static final String[] VALID_POS = new String[]{
            "NNP",
            "CD",
            "NNS",
            "NN"
    };

    public static List filterKeywords (InputObject inputObject) {
        LOGGER.info("Filtering keywords...");
        List<Keyword> keywords = new ArrayList<>();
        int sentenceId = 1;
        for (int i = 0; i < inputObject.getLemmas().size(); i++) {
            if (inputObject.getLemmas().get(i).equals(".")) {
                sentenceId++;
                LOGGER.info("increment Sentence id: {}", sentenceId);
                continue;
            }
            for (String pos: VALID_POS) {
                if(pos.equals(inputObject.getPoses().get(i))) {
                    LOGGER.info("Adding keyword: {}", inputObject.getLemmas().get(i));
                    keywords.add(new Keyword(inputObject.getId(), sentenceId, inputObject.getLemmas().get(i), pos));
                    break;
                }
            }
        }
        LOGGER.info("Returning kewords list...");
        return keywords;
    }
}
