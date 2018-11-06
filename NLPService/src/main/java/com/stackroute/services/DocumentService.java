package com.stackroute.services;

import com.stackroute.domain.DocumentReader;
import com.stackroute.domain.OutputForDoc;
import edu.stanford.nlp.simple.Document;

import java.util.List;

public interface DocumentService {

    public List<DocumentReader> saveDocuments(List<DocumentReader> documentReader);

    public List<DocumentReader> getAllDocuments();

    public double tf(List<String> doc, String term);

    public double idf(List<List<String>> docs, String term);

    public List<String> tfIdf(int index, List<List<String>> docs);

    public List<OutputForDoc> processDoc(List<DocumentReader> documentReaders);

    public List<Document>convertStringToDocument(List<DocumentReader> documentReaders);
}