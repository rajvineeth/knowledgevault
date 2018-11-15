package com.stackroute.knowledgevault.populator.service;

import com.stackroute.knowledgevault.populator.repository.CauseRepo;
import com.stackroute.knowledgevault.domain.Cause;
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
