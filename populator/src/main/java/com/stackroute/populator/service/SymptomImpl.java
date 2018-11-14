package com.stackroute.populator.service;

import com.stackroute.populator.domain.Symptom;
import com.stackroute.populator.repository.SymptomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SymptomImpl implements SymptomService {
    private SymptomRepo symptomRepo;
    @Autowired
    public SymptomImpl(SymptomRepo symptomRepo){
        this.symptomRepo=symptomRepo;
    }
    @Override
    public Symptom saveSymptom(Symptom symptom) {
        return symptomRepo.save(symptom);
    }

    @Override
    public List<Symptom> symptomList() {
        List<Symptom> symptomList = (List)symptomRepo.findAll();
        return symptomList;
    }
}
