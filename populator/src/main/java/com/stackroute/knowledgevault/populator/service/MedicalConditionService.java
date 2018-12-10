package com.stackroute.knowledgevault.populator.service;

import com.stackroute.knowledgevault.domain.MedicalCondition;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface MedicalConditionService {
    public MedicalCondition saveCondition(MedicalCondition medicalCondition) ;
}
