package com.stackroute.populator.services;

import com.stackroute.knowledgevault.domain.MedicalCondition;
import com.stackroute.knowledgevault.populator.repository.MedicalConditionRepo;
import com.stackroute.knowledgevault.populator.service.MedicalConditionImpl;
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


public class MedicalConditionServiceTest {
    MedicalCondition medicalCondition;

    @Mock
    MedicalConditionRepo medicalConditionRepo;

    @InjectMocks
    MedicalConditionImpl medicalConditionService;

    List<MedicalCondition> list= null;

    @Before
    public void setUp(){
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        medicalCondition =new MedicalCondition();
        medicalCondition.setName("cancer");
        medicalCondition.setType("MedicalCondition");
        list = new ArrayList<>();
        list.add(medicalCondition);
    }

    @After
    public void tearDown() throws Exception {
        medicalConditionRepo.deleteAll();
    }

    @Test
    public void saveConditionTestSuccess() {
        when(medicalConditionRepo.save((MedicalCondition) any())).thenReturn(medicalCondition);
        MedicalCondition savedMedicalCondition=medicalConditionService.saveCondition(medicalCondition);
        Assert.assertEquals(medicalCondition,savedMedicalCondition);
        verify(medicalConditionRepo,times(1)).save(medicalCondition);
    }
}
