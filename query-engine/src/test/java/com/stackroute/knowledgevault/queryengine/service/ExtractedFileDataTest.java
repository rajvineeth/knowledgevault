package com.stackroute.knowledgevault.queryengine.service;



import com.stackroute.knowledgevault.domain.ExtractedFileData;
import com.stackroute.knowledgevault.queryengine.repository.ExtractedFileDataRepo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ExtractedFileDataTest {
    ExtractedFileData extractedFileData;
    @Mock
    ExtractedFileDataRepo extractedFileDataRepo;
    @InjectMocks
    ExtractedDataService extractedDataService;
    List<ExtractedFileData> list=null;
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        extractedFileData=new ExtractedFileData();
        extractedFileData.setId(1);
        extractedFileData.setContent("content");
        extractedFileData.setMetadata("meta");
        list=new ArrayList<>();
        list.add(extractedFileData);
    }
    @After
    public void tearDown() throws Exception{
        extractedFileDataRepo.deleteAll();
    }
    @Test
    public void getDocById() {
        when(extractedFileDataRepo.findById(any())).thenReturn(java.util.Optional.ofNullable(extractedFileData));
        Optional<ExtractedFileData> extractedFileData1=extractedDataService.getDocById(1);
        Assert.assertSame(extractedFileData,extractedFileData1.get());
    }
}
