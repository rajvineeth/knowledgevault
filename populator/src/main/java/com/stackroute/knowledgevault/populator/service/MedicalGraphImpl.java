package com.stackroute.knowledgevault.populator.service;

import com.stackroute.knowledgevault.domain.*;
import com.stackroute.knowledgevault.populator.repository.MTRRepo;
import com.stackroute.knowledgevault.populator.repository.MedicalGraphRepo;
import org.neo4j.driver.v1.*;
import org.neo4j.driver.v1.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.stereotype.Service;

import java.io.File;
import java.sql.*;
import java.util.List;
import java.util.Optional;

import static org.neo4j.driver.v1.Values.parameters;

@Service
public class MedicalGraphImpl implements MedicalGraphService {
    @Autowired
    MTRRepo mtrRepo;
    @Autowired
    MedicalConditionService medicalConditionService;
    @Autowired
    private AnatomyService anatomyService;
    @Autowired
    private SymptomService symptomService;
    @Autowired
    MedicalGraphRepo medicalGraphRepo;




    public void populate(Long id, MedicalCondition medicalCondition, Anatomy anatomy, List<MedicalSymptom> medicalSymptomList){


        medicalConditionService.saveCondition(medicalCondition);
        anatomyService.saveAnatomy(anatomy);
        List<MTR> mtrList = mtrRepo.getRelations(medicalCondition.getType(), medicalSymptomList.get(0).getType());
        //List<MTR> mtrSAList=mtrRepo.getRelations()
        for(MedicalSymptom medicalSymptom : medicalSymptomList) {
            symptomService.saveSymptom(medicalSymptom);

            String rel="";
            for(MTR mtr:mtrList){
                rel=mtr.getType();
                QueryDriverService example = new QueryDriverService("bolt://localhost", "neo4j", "123456");
                example.createRel(medicalCondition,rel,medicalSymptom);
                example.close();
            }
        }
        medicalCondition.setMedicalSymptomList(medicalSymptomList);
    }
    public void makegraph(int id, MedicalCondition medicalCondition, Anatomy anatomy, List<MedicalSymptom> medicalSymptomList){
        anatomyService.saveAnatomy(anatomy);

        for(MedicalSymptom medicalSymptom : medicalSymptomList) {
            symptomService.saveSymptom(medicalSymptom);
            List<MTR> mtrList = mtrRepo.getRelations("MedicalCondition", "MedicalSymptom");
            String rel="";
            for(MTR mtr:mtrList){
                rel=mtr.getType();
            }
        }
        medicalCondition.setMedicalSymptomList(medicalSymptomList);
        medicalCondition.setAnatomy(anatomy);
        medicalConditionService.saveCondition(medicalCondition);
    }

}
