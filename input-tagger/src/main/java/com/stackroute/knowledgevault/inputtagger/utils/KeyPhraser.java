package com.stackroute.knowledgevault.inputtagger.utils;

import com.stackroute.knowledgevault.domain.InputObject;
import com.stackroute.knowledgevault.domain.Keyword;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class KeyPhraser {
    private static final Logger LOGGER = LoggerFactory.getLogger(KeyPhraser.class);

    public static List<Keyword> phrase(InputObject inputObject) {

        return null;
    }

    public static int phraseNNs (InputObject inputObject, int sentenceId, int i, List<Keyword> keyPhrases) {
        Keyword keyword = new Keyword(inputObject.getId(), sentenceId, inputObject.getLemmas().get(i), "NN");
        while(inputObject.getPoses().get(++i).equals("NN")) {
            keyword.getLemma().concat(" " + inputObject.getLemmas().get(i));
        }
        keyPhrases.add(keyword);
        return i;
    }
}
