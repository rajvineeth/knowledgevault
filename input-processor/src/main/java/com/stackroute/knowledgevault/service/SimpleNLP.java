package com.stackroute.knowledgevault.service;

import edu.stanford.nlp.simple.Sentence;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
}
