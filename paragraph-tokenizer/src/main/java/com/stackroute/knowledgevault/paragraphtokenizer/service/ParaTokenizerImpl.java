package com.stackroute.knowledgevault.paragraphtokenizer.service;

import com.stackroute.knowledgevault.domain.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * this class uses simple regex exp to tokenize the document text into different paragraphs,
 * using .split(regex) method.
 */

@Component
public class ParaTokenizerImpl implements ParaTokenizer {

    @Value("${consumed.list}")
    private String docList;

    private static final Logger LOGGER = LoggerFactory.getLogger(ParaTokenizerImpl.class);

    /*
     * Takes in documents as input and then splits the documents into smaller documents
     * and returns a list of documents.
     */
    @Override
    public List tokenizePara(Document document) {
        List list = new ArrayList<>();
        String text = document.getText();
        String[] arrText = text.trim().split("\n+");

        for (int i = 0; i < arrText.length; i++){
            list.add(new Document(document.getId(),arrText[i]));
        }

        LOGGER.info(docList, list);
        return list;
    }

}
