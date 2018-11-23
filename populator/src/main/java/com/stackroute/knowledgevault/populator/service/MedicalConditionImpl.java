package com.stackroute.knowledgevault.populator.service;

import com.stackroute.knowledgevault.populator.repository.MedicalConditionRepo;
import com.stackroute.knowledgevault.domain.MedicalCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalConditionImpl implements MedicalConditionService {

    private MedicalConditionRepo medicalConditionRepo;
    @Autowired
    public MedicalConditionImpl(MedicalConditionRepo medicalConditionRepo){
        this.medicalConditionRepo=medicalConditionRepo;
    }
    @Override
    public MedicalCondition saveCondition(MedicalCondition medicalCondition) {
        return medicalConditionRepo.save(medicalCondition);
    }


}
