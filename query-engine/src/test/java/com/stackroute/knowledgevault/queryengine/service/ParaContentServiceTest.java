package com.stackroute.knowledgevault.queryengine.service;

import com.stackroute.knowledgevault.domain.ParaContent;
import com.stackroute.knowledgevault.queryengine.repository.ParaContentRepo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ParaContentServiceTest {
    ParaContent paraContent;
    @Mock
    ParaContentRepo paraContentRepo;
    @InjectMocks
    ParaContentService paraContentService;
    List<ParaContent> list=null;
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        paraContent=new ParaContent();
        paraContent.setParaId(1);
        paraContent.setDocId(1);
        paraContent.setData("para data");
        list=new ArrayList<>();
        list.add(paraContent);
    }
    @After
    public void tearDown() throws Exception{
        paraContentRepo.deleteAll();
    }
    @Test
    public void getParaById() {
        when(paraContentRepo.findById(any())).thenReturn(java.util.Optional.ofNullable(paraContent));
        Optional<ParaContent> paraContent1=paraContentService.getParaById(1);
        Assert.assertSame(paraContent,paraContent1.get());
    }
}
