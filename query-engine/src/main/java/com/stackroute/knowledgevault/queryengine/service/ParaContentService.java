package com.stackroute.knowledgevault.queryengine.service;

import com.stackroute.knowledgevault.domain.ParaContent;
import com.stackroute.knowledgevault.queryengine.repository.ParaContentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParaContentService {

    private ParaContentRepo paraContentRepo;
    @Autowired
    public ParaContentService(ParaContentRepo paraContentRepo){
        this.paraContentRepo=paraContentRepo;
    }
    public Optional<ParaContent> getParaById(int id)  {
        Optional<ParaContent> paraContent;
        paraContent=paraContentRepo.findById(id);
        return paraContent;
    }
}
