package com.stackroute.knowledgevault.services;

import com.stackroute.knowledgevault.domain.ExtractedFileData;
import com.stackroute.knowledgevault.domain.OutputForDoc;
import edu.stanford.nlp.simple.Document;

import java.util.List;

public interface DocumentService {

    public List<ExtractedFileData> saveDocuments(List<ExtractedFileData> extractedFileData);

    public List<ExtractedFileData> getAllDocuments();

    public double tf(List<String> doc, String term);

    public double idf(List<List<String>> docs, String term);

    public List<String> tfIdf(int index, List<List<String>> docs);

    public List<OutputForDoc> processDoc(List<ExtractedFileData> extractedFileData);

    public List<Document>convertStringToDocument(List<ExtractedFileData> extractedFileData);
}