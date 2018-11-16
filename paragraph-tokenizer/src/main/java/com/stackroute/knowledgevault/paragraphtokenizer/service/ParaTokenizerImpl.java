package com.stackroute.knowledgevault.paragraphtokenizer.service;

import com.stackroute.knowledgevault.domain.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
public class ParaTokenizerImpl implements ParaTokenizer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ParaTokenizerImpl.class);

    @Override
    public List<Document> tokenizePara(Document document) {
        List<Document> list = new ArrayList<>();
        String text = document.getText();
        String[] arrText = text.trim().split("\n+");

        for (int i = 0; i < arrText.length; i++){
            list.add(new Document(document.getId(),arrText[i]));
        }

        LOGGER.info("List of documents: {}", list);
        return list;
    }

}
