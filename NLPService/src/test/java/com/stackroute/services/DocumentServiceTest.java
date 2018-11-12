package com.stackroute.services;

import com.stackroute.domain.ExtractedFileData;
import com.stackroute.repository.DocumentRepository;
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
        extractedFileData.setId(1);
        extractedFileData.setMetadata("this is metadata");
        extractedFileData.setContent("I am so done with this service. Seriously, why does it even exist. All it does is take stuff and find relevant terms using statistics");
        list = new ArrayList<>();
        list.add(extractedFileData);
    }

    @Test
    public void saveDocumentTestSuccess() {
        when(documentRepository.saveAll((Iterable<ExtractedFileData>) any())).thenReturn(list);
        List<ExtractedFileData> savedDocs = documentService.saveDocuments(list);
        Assert.assertEquals(list, savedDocs);

        verify(documentRepository,times(1)).saveAll(list);
    }

    

}
