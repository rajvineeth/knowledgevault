package com.stackroute.knowledgevault.documentidentifier.services;

import com.stackroute.knowledgevault.domain.ExtractedFileData;
import com.stackroute.knowledgevault.domain.JsonLDObject;
import com.stackroute.knowledgevault.domain.OutputForDoc;
import edu.stanford.nlp.simple.Document;


import java.util.List;

public interface DocumentService {

    public ExtractedFileData saveDocuments(ExtractedFileData extractedFileData);

    public List<ExtractedFileData> getAllDocuments();

    public double tf(List<String> doc, String term);

    public double idf(List<List<String>> docs, String term);

    public List<String> tfIdf(int index, List<List<String>> docs);

    public List<OutputForDoc> processDoc(List<ExtractedFileData> extractedFileData, int n);

    public List<Document>convertStringToDocument(List<ExtractedFileData> extractedFileData);

    public List<JsonLDObject> getJsonLD(List<OutputForDoc> outputForDocs);
}