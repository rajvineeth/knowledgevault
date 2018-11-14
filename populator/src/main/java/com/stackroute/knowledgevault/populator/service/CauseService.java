package com.stackroute.knowledgevault.populator.service;

import com.stackroute.knowledgevault.domain.Cause;
import java.util.List;


public interface CauseService{
    public Cause saveCause(Cause cause) ;
    public List<Cause> causeList();
}
