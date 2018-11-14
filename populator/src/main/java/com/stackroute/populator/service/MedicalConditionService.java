package com.stackroute.populator.service;

import com.stackroute.populator.domain.MedicalCondition;

import java.util.List;


public interface MedicalConditionService {
    public MedicalCondition saveCondition(MedicalCondition medicalCondition) ;
    public List<MedicalCondition> conditionList();
}
