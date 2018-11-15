package com.stackroute.knowledgevault.paragraphtokenizer.service;

import edu.stanford.nlp.simple.Sentence;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SimpleNLP {

    List lemmatize (Sentence sentence) {
        return sentence.lemmas();

    }

    List posTag (Sentence sentence) {
        return sentence.posTags();
    }

    List tokenize (Sentence sentence) {
        return sentence.words();
    }

    List nerTagging (Sentence sentence) {
        return sentence.nerTags();
    }
}
