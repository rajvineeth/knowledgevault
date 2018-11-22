package com.stackroute.populator.services;

import com.stackroute.knowledgevault.domain.MedicalSymptom;
import com.stackroute.knowledgevault.populator.repository.SymptomRepo;
import com.stackroute.knowledgevault.populator.service.SymptomImpl;
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


public class SymptomServiceTest {
    MedicalSymptom symptom;

    @Mock
    SymptomRepo symptomRepo;

    @InjectMocks
    SymptomImpl symptomService;

    List<MedicalSymptom> list= null;

    @Before
    public void setUp(){
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        symptom =new MedicalSymptom();
        symptom.setName("stomach pain");
        symptom.setType("MedicalSymptom");
        list = new ArrayList<>();
        list.add(symptom);
    }
    @After
    public void tearDown() throws Exception {
        symptomRepo.deleteAll();
    }
    @Test
    public void saveSymptomTestSuccess() {
        when(symptomRepo.save((MedicalSymptom) any())).thenReturn(symptom);
        MedicalSymptom savedSymptom = symptomService.saveSymptom(symptom);
        Assert.assertEquals(symptom,savedSymptom);
        verify(symptomRepo,times(1)).save(symptom);
    }
}
