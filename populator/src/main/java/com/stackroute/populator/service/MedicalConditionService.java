package com.stackroute.populator.service;

import com.stackroute.populator.domain.MedicalCondition;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MedicalConditionService {
    public MedicalCondition saveCondition(MedicalCondition medicalCondition) ;
    public List<MedicalCondition> conditionList();
}
