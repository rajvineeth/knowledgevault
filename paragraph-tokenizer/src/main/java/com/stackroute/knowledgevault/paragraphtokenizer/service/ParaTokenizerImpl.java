package com.stackroute.knowledgevault.paragraphtokenizer.service;

import com.stackroute.knowledgevault.domain.Document;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
public class ParaTokenizerImpl implements ParaTokenizer {

    @Override
    public List<Document> tokenizePara(Document document) {
        List<Document> list = new ArrayList<>();
        String text = document.getText();
        String[] arrText = text.trim().split("\n+");

        System.out.println("inside tokenize para");
        for (int i = 0; i < arrText.length; i++){
            list.add(new Document(document.getId(),arrText[i]));
        }

        return list;
    }

}
