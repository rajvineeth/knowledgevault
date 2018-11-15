package com.stackroute.knowledgevault.service;

import com.stackroute.knowledgevault.domain.Input;
import edu.stanford.nlp.simple.Document;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SentenceSplitter {

    List splitDocument (Input input) {
        Document document = new Document(input.getText());
        return document.sentences();
    }
}
