package com.stackroute.knowledgevault.paragraphtokenizer.service;

import com.stackroute.knowledgevault.paragraphtokenizer.model.Document;

import java.util.List;

public class ParaTokenizerImpl implements ParaTokenizer {

    List<Document> list;

    @Override
    public List<Document> tokenizePara(Document document) {
        String text = document.getText();
        String[] arrText = text.trim().split("\n");

        for (int i = 0; i < arrText.length; i++){
            list.add(new Document(document.getId(),arrText[i]));
        }
        return list;
    }
}
