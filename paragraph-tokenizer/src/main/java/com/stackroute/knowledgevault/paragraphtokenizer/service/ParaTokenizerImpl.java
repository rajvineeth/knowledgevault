package com.stackroute.knowledgevault.service;

import com.stackroute.knowledgevault.domain.ExtractedFileData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParaTokenizerImpl implements ParaTokenizer {

    List<ExtractedFileData> list = new ArrayList<ExtractedFileData>();

    @Override
    public List<ExtractedFileData> tokenizePara(ExtractedFileData extraxtedFileData) {
        String text = extraxtedFileData.getContent();
        String[] arrText = text.trim().split("\n+");

        for (int i = 0; i < arrText.length; i++){
            list.add(new ExtractedFileData(extraxtedFileData.getId(),"",arrText[i]));
        }
        return list;
    }
}
