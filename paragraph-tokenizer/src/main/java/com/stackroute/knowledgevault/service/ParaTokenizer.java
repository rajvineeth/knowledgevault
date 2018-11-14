package com.stackroute.knowledgevault.service;

import com.stackroute.knowledgevault.domain.ExtraxtedFileData;

import java.util.List;

public interface ParaTokenizer {
    List<ExtraxtedFileData> tokenizePara(ExtraxtedFileData extraxtedFileData);
}
