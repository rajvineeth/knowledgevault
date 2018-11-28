package com.stackroute.populator.services;

import com.stackroute.knowledgevault.domain.Content;
import com.stackroute.knowledgevault.populator.repository.ContentRepo;
import com.stackroute.knowledgevault.populator.service.ContentService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ContentServiceTest {
    Content content;

    @Mock
    ContentRepo contentRepo;

    @InjectMocks
    ContentService contentService;

    ArrayList<Integer> list= null;

    @Before
    public void setUp(){
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        content =new Content();
        content.setName("stomach");
        content.setType("AnatomicalStructure");
        content.setId(1);
        list = new ArrayList<Integer>();
        list.add(new Integer(1));
        content.setParaId(list);
    }

    @After
    public void tearDown() throws Exception {
        contentRepo.deleteAll();
    }

    @Test
    public void saveContentTestSuccess() {
        when(contentRepo.save((Content) any())).thenReturn(content);
        Content savedContent = contentService.saveContent(content);
        Assert.assertEquals(savedContent,content);
        verify(contentRepo,times(1)).save(content);
    }
}
