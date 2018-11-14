package com.stackroute.populator.service;

import com.stackroute.populator.domain.Cause;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CauseService{
    public Cause saveCause(Cause cause) ;
    public List<Cause> causeList();
}
