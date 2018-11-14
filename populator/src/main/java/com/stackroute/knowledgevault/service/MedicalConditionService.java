package com.stackroute.knowledgevault.service;

import com.stackroute.knowledgevault.domain.MedicalCondition;

import java.util.List;


public interface MedicalConditionService {
    public MedicalCondition saveCondition(MedicalCondition medicalCondition) ;
    public List<MedicalCondition> conditionList();
}
