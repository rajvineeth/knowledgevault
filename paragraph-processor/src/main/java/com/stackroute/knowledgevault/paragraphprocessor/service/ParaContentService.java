package com.stackroute.knowledgevault.paragraphprocessor.service;

import com.stackroute.knowledgevault.domain.ParaContent;
import com.stackroute.knowledgevault.paragraphprocessor.repository.ParaContentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParaContentService {
    @Autowired
    private ParaContentRepo paraContentRepo;

    public ParaContentService(ParaContentRepo paraContentRepo) {
        this.paraContentRepo = paraContentRepo;
    }
    public ParaContent saveContent(ParaContent paraContent) {
        return paraContentRepo.save(paraContent);
    }
}
