package com.stackroute.populator.service;

import com.stackroute.populator.domain.Symptom;

import java.util.List;

public interface SymptomService {
    public Symptom saveSymptom(Symptom symptom) ;
    public List<Symptom> symptomList();
}
