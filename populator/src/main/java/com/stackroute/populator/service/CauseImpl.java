package com.stackroute.populator.service;

import com.stackroute.populator.domain.Cause;
import com.stackroute.populator.repository.CauseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CauseImpl implements CauseService {

    private CauseRepo causeRepo;
    @Autowired
    public CauseImpl(CauseRepo causeRepo){
        this.causeRepo=causeRepo;
    }
    @Override
    public Cause saveCause(Cause cause) {
        return causeRepo.save(cause);
    }
    @Override
    public List<Cause> causeList() {
        List<Cause> listcauses = (List)causeRepo.findAll();
        return listcauses;
    }
}
