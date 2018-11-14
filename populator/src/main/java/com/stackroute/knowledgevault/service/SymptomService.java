package com.stackroute.knowledgevault.service;

import com.stackroute.knowledgevault.domain.Symptom;

import java.util.List;

public interface SymptomService {
    public Symptom saveSymptom(Symptom symptom) ;
    public List<Symptom> symptomList();
}
