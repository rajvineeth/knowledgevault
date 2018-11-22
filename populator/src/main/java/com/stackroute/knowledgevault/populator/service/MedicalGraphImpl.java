package com.stackroute.knowledgevault.populator.service;

import com.stackroute.knowledgevault.domain.*;
import com.stackroute.knowledgevault.populator.repository.MTRRepo;
import com.stackroute.knowledgevault.populator.repository.MedicalGraphRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

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

    public void populate(int id, MedicalCondition medicalCondition, AnatomicalStructure anatomicalStructure, List<MedicalSymptom> medicalSymptomList) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        medicalConditionService.saveCondition(medicalCondition);
        anatomyService.saveAnatomy(anatomicalStructure);
        MTR mtrCS = mtrRepo.getRelation(medicalCondition.getType(), medicalSymptomList.get(0).getType());
        MTR mtrSC = mtrRepo.getRelation(medicalSymptomList.get(0).getType(),medicalCondition.getType());
        MTR mtrAC = mtrRepo.getRelation(anatomicalStructure.getType(),medicalCondition.getType());
        MTR mtrCA = mtrRepo.getRelation(medicalCondition.getType(), anatomicalStructure.getType());
        QueryDriverService example = new QueryDriverService("bolt://localhost", "neo4j", "123456");

        for(MedicalSymptom medicalSymptom : medicalSymptomList) {
            symptomService.saveSymptom(medicalSymptom);
            String rel1="";
                rel1=mtrCS.getType();
                example.createRel(medicalCondition,rel1,medicalSymptom);
            String rel2="";
            rel2=mtrSC.getType();
            example.createRel(medicalSymptom,rel2,medicalCondition);
        }
        System.out.println(mtrAC.getType()+" "+mtrCA.getType());
        String relAC="",relCA="";
        relAC=mtrAC.getType();
        relCA=mtrCA.getType();
        example.createRel(anatomicalStructure,relAC,medicalCondition);
        example.createRel(medicalCondition,relCA, anatomicalStructure);
        example.close();
    }
    public void makegraph(int id, MedicalCondition medicalCondition, AnatomicalStructure anatomicalStructure, List<MedicalSymptom> medicalSymptomList){
        if(anatomicalStructure !=null) {
            anatomyService.saveAnatomy(anatomicalStructure);
            medicalCondition.setAnatomicalStructure(anatomicalStructure);
        }
        if(medicalSymptomList!=null) {
            for (MedicalSymptom medicalSymptom : medicalSymptomList) {
                symptomService.saveSymptom(medicalSymptom);
//                List<MTR> mtrList = (List<MTR>) mtrRepo.getRelation("MedicalCondition", "MedicalSymptom");
//                String rel = "";
//                for (MTR mtr : mtrList) {
//                    rel = mtr.getType();
//                }
            }
            medicalCondition.setMedicalSymptomList(medicalSymptomList);
        }
        if(medicalCondition!=null) {
            medicalConditionService.saveCondition(medicalCondition);
        }
    }

}
