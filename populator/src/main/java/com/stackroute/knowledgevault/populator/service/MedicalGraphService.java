package com.stackroute.knowledgevault.populator.service;

import com.stackroute.knowledgevault.domain.AnatomicalStructure;
import com.stackroute.knowledgevault.domain.MedicalCondition;
import com.stackroute.knowledgevault.domain.MedicalSymptom;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface MedicalGraphService {
    public void populate(int id, MedicalCondition medicalCondition, AnatomicalStructure anatomicalStructure, List<MedicalSymptom> medicalSymptomList) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;
    public void makegraph(int id, MedicalCondition medicalCondition, AnatomicalStructure anatomicalStructure, List<MedicalSymptom> medicalSymptomList);
}
