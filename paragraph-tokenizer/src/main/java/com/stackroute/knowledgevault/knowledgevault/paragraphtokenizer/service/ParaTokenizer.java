package com.stackroute.knowledgevault.knowledgevault.paragraphtokenizer.service;

import com.stackroute.knowledgevault.knowledgevault.paragraphtokenizer.model.Document;

import java.util.List;

public interface ParaTokenizer {
    List<Document> tokenizePara(Document document);
}
