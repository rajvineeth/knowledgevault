package com.stackroute.populator.service;

import com.stackroute.populator.domain.Anatomy;

import java.util.List;

public interface AnatomyService {
    public Anatomy saveAnatomy(Anatomy anatomy) ;
    public List<Anatomy> anatomyList();
}
