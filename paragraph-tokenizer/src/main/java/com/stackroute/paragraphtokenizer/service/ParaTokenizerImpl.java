package com.stackroute.paragraphtokenizer.service;

import com.stackroute.domain.ExtraxtedFileData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParaTokenizerImpl implements ParaTokenizer {

    List<ExtraxtedFileData> list = new ArrayList<ExtraxtedFileData>();

    @Override
    public List<ExtraxtedFileData> tokenizePara(ExtraxtedFileData extraxtedFileData) {
        String text = extraxtedFileData.getContent();
        String[] arrText = text.trim().split("\n+");

        for (int i = 0; i < arrText.length; i++){
            list.add(new ExtraxtedFileData(extraxtedFileData.getId(),arrText[i]));
        }
        return list;
    }
}
