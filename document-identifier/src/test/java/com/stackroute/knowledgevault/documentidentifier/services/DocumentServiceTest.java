package com.stackroute.knowledgevault.documentidentifier.services;

import com.stackroute.knowledgevault.domain.*;
import com.stackroute.knowledgevault.documentidentifier.repository.*;
import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

public class DocumentServiceTest {

    private ExtractedFileData extractedFileData;

    @Mock
    DocumentRepository documentRepository;

    @InjectMocks
    DocumentServiceImpl documentService;
    private List<ExtractedFileData> list = null;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        extractedFileData = new ExtractedFileData();
        ExtractedFileData extractedFileData1 = new ExtractedFileData();
        extractedFileData.setId(1);
        extractedFileData.setMetadata("this is metadata");
        extractedFileData.setContent("Cancer is a group of diseases involving abnormal cell growth with the potential to invade or spread to other parts of the body.[2][8] These contrast with benign tumors, which do not spread to other parts of the body.[8] Possible signs and symptoms include a lump, abnormal bleeding, prolonged cough, unexplained weight loss and a change in bowel movements.[1] While these symptoms may indicate cancer, they may have other causes.[1] Over 100 types of cancers affect humans.[8]\n");
        list = new ArrayList<>();
        list.add(extractedFileData);
        extractedFileData1.setId(2);
        extractedFileData1.setMetadata("this is also metadata");
        extractedFileData1.setContent("why is this service is even happening, stop making this happen. I swear to God, I will SIGKILL myself");
        list.add(extractedFileData1);
    }

    @Test
    public void saveDocumentTestSuccess() {
        when(documentRepository.save((ExtractedFileData) any())).thenReturn(extractedFileData);
        ExtractedFileData savedDocs = documentService.saveDocuments(extractedFileData);
        Assert.assertEquals(extractedFileData, savedDocs);


    }

    @Test
    public void getDocumentsTest() {
        documentRepository.saveAll(list);
        when(documentRepository.findAll()).thenReturn(list);
        List<ExtractedFileData> extractedFileDataList = documentService.getAllDocuments();
        Assert.assertEquals(list, extractedFileDataList);
    }

    @Test
    public void tfTest(){

        List<Document> stringtoDocument = documentService.convertStringToDocument(list);
        List<String> terms = new ArrayList<>();
        StopwordRemoval stopwordRemoval = new StopwordRemoval();
        for(Sentence sentence: stringtoDocument.get(0).sentences()){
            for(int i=0;i<sentence.length();i++){
                terms.add(sentence.lemma(i));
            }
        }
        //terms = stopwordRemoval.removeStopwords(terms);

        double tfScore = documentService.tf(terms, "Kushagra");
        Assert.assertEquals(0.0, tfScore,1e-4);
    }

    @Test
    public void idfTest() {
        List<Document> stringtoDocument = documentService.convertStringToDocument(list);
        List<List<String>> docs = new ArrayList<>();
        StopwordRemoval stopwordRemoval = new StopwordRemoval();

        for(Document document: stringtoDocument){
            List<String> terms = new ArrayList<>();
            for(Sentence sentence: document.sentences()){
                for(int i=0;i<sentence.length();i++){
                    terms.add(sentence.lemma(i));
                }
            }
            //terms = stopwordRemoval.removeStopwords(terms);
            docs.add(terms);
        }
        double idfScore = documentService.idf(docs,"statistics");

        Assert.assertEquals(0.6931,idfScore, 1e-4);

        idfScore = documentService.idf(docs, "service");
        Assert.assertEquals(0.0, idfScore, 1e-4);

    }

    @Test
    public void tfIdfTest() {
        List<Document> stringtoDocument = documentService.convertStringToDocument(list);
        List<List<String>> docs = new ArrayList<>();
        StopwordRemoval stopwordRemoval = new StopwordRemoval();

        for(Document document: stringtoDocument){
            List<String> terms = new ArrayList<>();
            for(Sentence sentence: document.sentences()){
                for(int i=0;i<sentence.length();i++){
                    terms.add(sentence.lemma(i));
                }
            }
            //terms = stopwordRemoval.removeStopwords(terms);
            docs.add(terms);
        }
        String[] relevantTerms = {"cancer", "group", "disease", "growth", "potential", "spread", "contrast", "tumor", "symptom", "cough", "weight", "change", "bowel", "movement", "cause"};
        List<String> tfIdf = documentService.tfIdf(0,docs);
        Assert.assertArrayEquals(relevantTerms, tfIdf.toArray());
    }
}
