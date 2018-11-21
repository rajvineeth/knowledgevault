package com.stackroute.knowledgevault.populator.service;

import com.stackroute.knowledgevault.domain.MedicalSymptom;

import java.util.List;

public interface SymptomService {
    public MedicalSymptom saveSymptom(MedicalSymptom medicalSymptom) ;
    public List<MedicalSymptom> symptomList();
}
