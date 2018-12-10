package com.stackroute.knowledgevault.populator.loaders;

import com.stackroute.knowledgevault.populator.repository.MTRRepo;
import com.stackroute.knowledgevault.populator.repository.StructureRepo;
import com.stackroute.knowledgevault.populator.service.MedicalConditionImpl;
import com.stackroute.knowledgevault.populator.service.MedicalGraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@Component
public class Loaders implements ApplicationListener<ContextRefreshedEvent>{
    private StructureRepo structureRepo;
    @Autowired
    private MedicalGraphService medicalGraphService;

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
        try {
            medicalGraphService.input("input",100);
            medicalGraphService.input("input1",101);
            medicalGraphService.input("input2",102);
            medicalGraphService.input("input3",103);
            medicalGraphService.input("input4",104);
            medicalGraphService.input("input5",105);
            medicalGraphService.input("input6",106);
            medicalGraphService.input("input7",107);
            medicalGraphService.input("input8",108);
            medicalGraphService.input("input9",109);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}