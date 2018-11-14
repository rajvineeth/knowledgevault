package com.stackroute.paragraphtokenizer.service;

import com.stackroute.domain.ExtraxtedFileData;

import java.util.List;

public interface ParaTokenizer {
    List<ExtraxtedFileData> tokenizePara(ExtraxtedFileData extraxtedFileData);
}
