package com.stackroute.knowledgevault.populator.service;

import com.stackroute.knowledgevault.domain.Content;
import com.stackroute.knowledgevault.domain.Document;
import com.stackroute.knowledgevault.populator.repository.ContentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentService {
    @Autowired
    private ContentRepo contentRepo;

    public ContentService(ContentRepo contentRepo) {
        this.contentRepo = contentRepo;
    }
    public Content saveContent(Content content) {
        return contentRepo.save(content);
    }
    public List<Content> contentList() {
        List<Content> listcondtions = (List)contentRepo.findAll();
        return listcondtions;
    }
}
