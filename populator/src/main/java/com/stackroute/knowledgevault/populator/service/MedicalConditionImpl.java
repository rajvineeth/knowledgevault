package com.stackroute.knowledgevault.populator.service;

import com.github.jsonldjava.utils.JsonUtils;
import com.stackroute.knowledgevault.domain.*;
import com.stackroute.knowledgevault.populator.repository.MedicalConditionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
