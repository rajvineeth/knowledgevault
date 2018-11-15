package com.stackroute.knowledgevault.populator.service;

import com.stackroute.knowledgevault.domain.Anatomy;

import java.util.List;

public interface AnatomyService {
    public Anatomy saveAnatomy(Anatomy anatomy) ;
    public List<Anatomy> anatomyList();
}
