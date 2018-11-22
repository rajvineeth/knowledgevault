package com.stackroute.knowledgevault.populator.service;

import com.stackroute.knowledgevault.domain.AnatomicalStructure;
import com.stackroute.knowledgevault.domain.MTR;
import com.stackroute.knowledgevault.domain.MedicalCondition;
import com.stackroute.knowledgevault.domain.MedicalSymptom;
import com.stackroute.knowledgevault.populator.repository.MTRRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalGraphService {
    @Autowired
    MTRRepo mtrRepo;
    @Autowired
    MedicalConditionService medicalConditionService;
    @Autowired
    private AnatomyService anatomyService;
    @Autowired
    private SymptomService symptomService;

    public MedicalGraphService(MTRRepo mtrRepo, MedicalConditionService medicalConditionService, AnatomyService anatomyService, SymptomService symptomService) {
        this.mtrRepo = mtrRepo;
        this.medicalConditionService = medicalConditionService;
        this.anatomyService = anatomyService;
        this.symptomService = symptomService;
    }

    public Boolean populate(int id, MedicalCondition medicalCondition, AnatomicalStructure anatomicalStructure, List<MedicalSymptom> medicalSymptomList)  {
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
        String relAC="",relCA="";
        relAC=mtrAC.getType();
        relCA=mtrCA.getType();
            example.createRel(anatomicalStructure,relAC,medicalCondition);
            example.createRel(medicalCondition,relCA, anatomicalStructure);

        example.close();
        return true;
    }

}
