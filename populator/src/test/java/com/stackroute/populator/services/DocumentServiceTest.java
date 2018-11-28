package com.stackroute.populator.services;

import com.stackroute.knowledgevault.domain.Content;
import com.stackroute.knowledgevault.domain.Document;
import com.stackroute.knowledgevault.populator.repository.ContentRepo;
import com.stackroute.knowledgevault.populator.repository.DocumentRepo;
import com.stackroute.knowledgevault.populator.service.ContentService;
import com.stackroute.knowledgevault.populator.service.DocumentService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DocumentServiceTest {
    Document document;

    @Mock
    DocumentRepo documentRepo;

    @InjectMocks
    DocumentService documentService;

    ArrayList<Integer> list= null;

    @Before
    public void setUp(){
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        document =new Document();
        document.setName("stomach");
        document.setType("AnatomicalStructure");
        list = new ArrayList<Integer>();
        document.setId(list);
    }

    @After
    public void tearDown() throws Exception {
        documentRepo.deleteAll();
    }

    @Test
    public void saveContentTestSuccess() {
        when(documentRepo.save((Document) any())).thenReturn(document);
        Document savedDoc = documentService.saveDocument(document);
        Assert.assertEquals(savedDoc,document);
        verify(documentRepo,times(1)).save(document);
    }
}
