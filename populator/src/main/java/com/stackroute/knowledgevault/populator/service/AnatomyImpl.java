package com.stackroute.knowledgevault.populator.service;

import com.stackroute.knowledgevault.domain.AnatomicalStructure;
import com.stackroute.knowledgevault.populator.repository.AnatomyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnatomyImpl implements AnatomyService {
    private AnatomyRepo anatomyRepo;
    @Autowired
    public AnatomyImpl(AnatomyRepo anatomyRepo){
        this.anatomyRepo=anatomyRepo;
    }

    @Override
    public AnatomicalStructure saveAnatomy(AnatomicalStructure anatomicalStructure) {
        return anatomyRepo.save(anatomicalStructure);
    }

    @Override
    public List<AnatomicalStructure> anatomyList() {
        List<AnatomicalStructure> anatomicalStructureList = (List)anatomyRepo.findAll();
        return anatomicalStructureList;
    }
}
