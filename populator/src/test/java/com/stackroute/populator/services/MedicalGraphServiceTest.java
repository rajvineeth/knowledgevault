package com.stackroute.populator.services;

import com.stackroute.knowledgevault.domain.AnatomicalStructure;
import com.stackroute.knowledgevault.domain.MTR;
import com.stackroute.knowledgevault.domain.MedicalCondition;
import com.stackroute.knowledgevault.domain.MedicalSymptom;
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
    @Mock
    AnatomyService anatomyService;
    @Mock
    MTRRepo mtrRepo;
    @Mock
    QueryDriverService queryDriverService;

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
        medicalGraphService=new MedicalGraphService(mtrRepo,medicalConditionService,anatomyService,symptomService);
        medicalCondition.setName("cancer");
        medicalCondition.setType("MedicalCondition");
        medicalSymptom.setName("hair loss");
        medicalSymptom.setType("MedicalSymptom");
        symptomList.add(medicalSymptom);
        anatomicalStructure.setName("lungs");
        anatomicalStructure.setType("AnatomicalStructure");

    }

    @After
    public void tearDown() throws Exception {
        queryDriverService.close();

    }

    @Test
    public void populateTestSuccess() {
        when(queryDriverService.createRel((Object)any(),(String)any(),(Object)any())).thenReturn(true);
        when(mtrRepo.getRelation((String)any(),(String)any())).thenReturn(mtr);
        Boolean result=medicalGraphService.populate(1,medicalCondition,anatomicalStructure,symptomList);
        Assert.assertEquals(true,result);

        verify(medicalConditionService,times(1)).saveCondition(medicalCondition);
        verify(anatomyService,times(1)).saveAnatomy(anatomicalStructure);
        verify(symptomService,times(1)).saveSymptom(medicalSymptom);
        verify(mtrRepo,times(4)).getRelation((String) any(),(String) any());
    }
}
