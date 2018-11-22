package com.stackroute.populator.services;

import com.stackroute.knowledgevault.domain.AnatomicalStructure;
import com.stackroute.knowledgevault.populator.repository.AnatomyRepo;
import com.stackroute.knowledgevault.populator.service.AnatomyImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class AnatomyServiceTest {
    AnatomicalStructure anatomicalStructure;

    @Mock
    AnatomyRepo anatomyRepo;

    @InjectMocks
    AnatomyImpl anatomyService;

    List<AnatomicalStructure> list= null;

    @Before
    public void setUp(){
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        anatomicalStructure =new AnatomicalStructure();
        anatomicalStructure.setName("stomach");
        anatomicalStructure.setType("AnatomicalStructure");
        list = new ArrayList<>();
        list.add(anatomicalStructure);
    }

    @After
    public void tearDown() throws Exception {
        anatomyRepo.deleteAll();
    }

    @Test
    public void saveAnatomyTestSuccess() {
        when(anatomyRepo.save((AnatomicalStructure)any())).thenReturn(anatomicalStructure);
        AnatomicalStructure savedAnatomy = anatomyService.saveAnatomy(anatomicalStructure);
        Assert.assertEquals(anatomicalStructure,savedAnatomy);
        verify(anatomyRepo,times(1)).save(anatomicalStructure);
    }
}
