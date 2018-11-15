package com.stackroute.knowledgevault.paragraphtokenizer.service;

import com.stackroute.knowledgevault.domain.ExtractedFileData;

import java.util.List;

public interface ParaTokenizer {
    List<ExtractedFileData> tokenizePara(ExtractedFileData extraxtedFileData);
}
