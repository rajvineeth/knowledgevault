package com.stackroute.knowledgevault.populator.loaders;

import com.stackroute.knowledgevault.populator.repository.MTRRepo;
import com.stackroute.knowledgevault.populator.repository.StructureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class Loaders implements ApplicationListener<ContextRefreshedEvent>{
    private StructureRepo structureRepo;
    private static final String MEDICALCONDITION="MedicalCondition";
    private static final String MEDICALSYMPTOM="MedicalSymptom";
    private static final String ANATOMICALSTRUCTURE="AnatomicalStructure";
    @Autowired
    private MTRRepo mtrRepo;

    public Loaders(StructureRepo structureRepo) {
        this.structureRepo=structureRepo;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        structureRepo.createMT(MEDICALCONDITION);
        structureRepo.createMT(MEDICALSYMPTOM);
        structureRepo.createMT(ANATOMICALSTRUCTURE);
        structureRepo.createMT("Document");
        structureRepo.createMT("Content");
        structureRepo.createMTR("causedBy");
        structureRepo.createMTR("indicates");
        structureRepo.createMTR("affects");
        structureRepo.createMTR("affectedBy");
        structureRepo.createMTR("relatedDoc");
        structureRepo.createMTR("relatedContent");
        structureRepo.createR(MEDICALSYMPTOM,"indicates",MEDICALCONDITION);
        structureRepo.createR(MEDICALCONDITION,"relatedDoc","Document");
        structureRepo.createR(MEDICALCONDITION,"relatedContent","Content");
        structureRepo.createR(MEDICALCONDITION,"causedBy",MEDICALSYMPTOM);
        structureRepo.createR(MEDICALCONDITION,"affects",ANATOMICALSTRUCTURE);
        structureRepo.createR(ANATOMICALSTRUCTURE,"affectedBy",MEDICALCONDITION);
    }
}