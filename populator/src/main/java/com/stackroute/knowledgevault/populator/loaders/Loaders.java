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
    @Autowired
    private MTRRepo mtrRepo;

    public Loaders(StructureRepo structureRepo) {
        this.structureRepo=structureRepo;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        structureRepo.createMT("MedicalCondition");
        structureRepo.createMT("MedicalSymptom");
        structureRepo.createMT("AnatomicalStructure");
        structureRepo.createMTR("causedBy");
        structureRepo.createMTR("indicates");
        structureRepo.createMTR("affects");
        structureRepo.createMTR("affectedBy");
        structureRepo.createR("MedicalSymptom","indicates","MedicalCondition");
        structureRepo.createR("MedicalCondition","causedBy","MedicalSymptom");
        structureRepo.createR("MedicalCondition","affects","AnatomicalStructure");
        structureRepo.createR("AnatomicalStructure","affectedBy","MedicalCondition");
    }
}