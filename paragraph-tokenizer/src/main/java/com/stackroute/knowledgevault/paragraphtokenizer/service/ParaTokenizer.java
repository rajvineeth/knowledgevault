package com.stackroute.knowledgevault.paragraphtokenizer.service;

import com.stackroute.knowledgevault.paragraphtokenizer.model.Document;

import java.util.List;

public interface ParaTokenizer {
    List<Document> tokenizePara(Document document);
}
