package com.stackroute.knowledgevault.paragraphtokenizer.service;

import com.stackroute.knowledgevault.domain.Document;

import java.util.List;

public interface ParaTokenizer {
    List tokenizePara(Document doc);
}
