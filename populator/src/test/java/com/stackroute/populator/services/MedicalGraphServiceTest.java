package com.stackroute.populator.services;

import com.stackroute.knowledgevault.domain.*;
import com.stackroute.knowledgevault.populator.repository.MTRRepo;
import com.stackroute.knowledgevault.populator.service.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MedicalGraphServiceTest {


    MedicalCondition medicalCondition;
    @Mock
    MedicalConditionService medicalConditionService;

    MedicalSymptom medicalSymptom;
    @Mock
    MTR mtr;
    @Mock
    SymptomService symptomService;

    AnatomicalStructure anatomicalStructure;
    Document document;
    Content content;
    @Mock
    AnatomyService anatomyService;
    @Mock
    MTRRepo mtrRepo;
    @Mock
    QueryDriverService queryDriverService;
    @Mock
    DocumentService documentService;
    @Mock
    ContentService contentService;
    @InjectMocks
    MedicalGraphService medicalGraphService;
    List<MedicalSymptom> symptomList=new ArrayList<>();

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        medicalCondition=new MedicalCondition();
        medicalSymptom=new MedicalSymptom();
        anatomicalStructure=new AnatomicalStructure();
        mtr=new MTR("type");
        medicalGraphService=new MedicalGraphService(mtrRepo,medicalConditionService,anatomyService,symptomService,documentService,contentService);
        medicalCondition.setName("cancer");
        medicalCondition.setType("MedicalCondition");
        medicalSymptom.setName("hair loss");
        medicalSymptom.setType("MedicalSymptom");
        symptomList.add(medicalSymptom);
        anatomicalStructure.setName("lungs");
        anatomicalStructure.setType("AnatomicalStructure");
        document=new Document();
        document.setName("cancer");
        document.setType("Document");
        content=new Content();

        ArrayList<Integer> list=new ArrayList<>();
        list.add(1);
        document.setId(list);
        content.setParaId(list);
        content.setId(1);
        content.setName("cancer");
        content.setType("Content");

    }

    @After
    public void tearDown() throws Exception {
        queryDriverService.close();

    }

    @Test
    public void populateTestSuccess() {
        when(queryDriverService.createRel((Object)any(),(String)any(),(Object)any())).thenReturn(true);
        when(mtrRepo.getRelation((String)any(),(String)any())).thenReturn(mtr);
        Boolean result=medicalGraphService.populate(document,medicalCondition,anatomicalStructure,symptomList);
        Assert.assertEquals(true,result);

        verify(medicalConditionService,times(1)).saveCondition(medicalCondition);
        verify(anatomyService,times(1)).saveAnatomy(anatomicalStructure);
        verify(symptomService,times(1)).saveSymptom(medicalSymptom);
        verify(mtrRepo,times(5)).getRelation((String) any(),(String) any());
    }
    @Test
    public void populateFromParaTestSuccess() {
        when(queryDriverService.createRel((Object)any(),(String)any(),(Object)any())).thenReturn(true);
        when(mtrRepo.getRelation((String)any(),(String)any())).thenReturn(mtr);
        Boolean result=medicalGraphService.populateFromPara(content,medicalCondition,anatomicalStructure,symptomList);
        Assert.assertEquals(true,result);

        verify(medicalConditionService,times(1)).saveCondition(medicalCondition);
        verify(anatomyService,times(1)).saveAnatomy(anatomicalStructure);
        verify(symptomService,times(1)).saveSymptom(medicalSymptom);
        verify(mtrRepo,times(5)).getRelation((String) any(),(String) any());
    }
}
