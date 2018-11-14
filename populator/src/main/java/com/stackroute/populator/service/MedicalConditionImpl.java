package com.stackroute.populator.service;

import com.stackroute.populator.domain.MedicalCondition;
import com.stackroute.populator.repository.MedicalConditionRepo;
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

    @Override
    public List<MedicalCondition> conditionList() {
        List<MedicalCondition> listcondtions = (List)medicalConditionRepo.findAll();
        return listcondtions;
    }
}
