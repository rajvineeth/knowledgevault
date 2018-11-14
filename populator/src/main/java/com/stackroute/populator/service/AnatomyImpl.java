package com.stackroute.populator.service;

import com.stackroute.populator.domain.Anatomy;
import com.stackroute.populator.repository.AnatomyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnatomyImpl implements AnatomyService{
    private AnatomyRepo anatomyRepo;
    @Autowired
    public AnatomyImpl(AnatomyRepo anatomyRepo){
        this.anatomyRepo=anatomyRepo;
    }

    @Override
    public Anatomy saveAnatomy(Anatomy anatomy) {
        return anatomyRepo.save(anatomy);
    }

    @Override
    public List<Anatomy> anatomyList() {
        List<Anatomy> anatomyList = (List)anatomyRepo.findAll();
        return anatomyList;
    }
}
