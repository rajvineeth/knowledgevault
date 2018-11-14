package com.stackroute.populator.service;


import com.stackroute.populator.domain.Treatment;

import java.util.List;

public interface TreatmentService {
    public Treatment saveTreatment(Treatment treatment) ;
    public List<Treatment> treatmentList();
}
