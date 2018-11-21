package com.stackroute.knowledgevault.populator.service;

import com.stackroute.knowledgevault.domain.Anatomy;
import com.stackroute.knowledgevault.domain.MedicalCondition;
import com.stackroute.knowledgevault.domain.MedicalSymptom;

import java.util.List;

public interface MedicalGraphService {
    public void populate(Long id, MedicalCondition medicalCondition, Anatomy anatomy, List<MedicalSymptom> medicalSymptomList);
    public void makegraph(int id, MedicalCondition medicalCondition, Anatomy anatomy, List<MedicalSymptom> medicalSymptomList);
}
