package com.stackroute.knowledgevault.populator.service;

import com.stackroute.knowledgevault.domain.MedicalCondition;
import org.springframework.stereotype.Service;


@Service
public interface MedicalConditionService {
    public MedicalCondition saveCondition(MedicalCondition medicalCondition) ;
}
