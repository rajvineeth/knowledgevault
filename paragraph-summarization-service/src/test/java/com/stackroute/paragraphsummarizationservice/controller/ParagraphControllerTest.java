package com.stackroute.paragraphsummarizationservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.paragraphsummarizationservice.domain.Paragraph;
import com.stackroute.paragraphsummarizationservice.exceptions.MovieAlreadyExistsException;
import com.stackroute.paragraphsummarizationservice.exceptions.MovieNotFoundException;
import com.stackroute.paragraphsummarizationservice.service.ParagraphService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ParagraphControllerTest {
    //find what is MockMvc
    @Autowired
    private MockMvc mockMvc;

    private Paragraph paragraph;

    @MockBean
    private ParagraphService movieService;
    @InjectMocks
    private ParagraphController paragraphController;

    private List<Paragraph> list =null;
    private List<Paragraph> resultList =null;

    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(paragraphController).build();
        paragraph = new Paragraph(12,"geethagovindam","telugu","avg");
        list = new ArrayList();
        list.add(paragraph);
        System.out.println(asJsonString(paragraph));
    }


    @Test
    public void saveMovie() throws Exception {
        when(movieService.addMovie((Paragraph) any())).thenReturn(paragraph);
        mockMvc.perform(MockMvcRequestBuilders.post("/paragraph-api/v1/paragraph")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(paragraph)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
        verify(movieService,times(1)).addMovie((Paragraph) any());
    }
    @Test
    public void saveMovieFailure() throws Exception {
        when(movieService.addMovie((Paragraph) any())).thenThrow(MovieAlreadyExistsException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/paragraph-api/v1/paragraph")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(paragraph)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
        verify(movieService,times(1)).addMovie((Paragraph) any());
    }
//
//    @Test
//    public void getAllMovies() throws Exception {
//        when(movieService.getAllMovies()).thenReturn(list);
//        mockMvc.perform(MockMvcRequestBuilders.get("/paragraph-api/v1/paragraph")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(paragraph)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//        verify(movieService,times(1)).getAllMovies();
//
//    }
//    @Test
    public void deleteMovie() throws Exception {
        when(movieService.deleteMovie((anyInt()))).thenReturn(resultList);

        mockMvc.perform(MockMvcRequestBuilders.delete("/paragraph-api/v1/paragraph/12")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(paragraph)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(movieService,times(1)).deleteMovie(anyInt());
    }
    @Test
    public void deleteMovieFailure() throws Exception {
        when(movieService.deleteMovie(anyInt())).thenThrow(MovieNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.delete("/paragraph-api/v1/paragraph/12")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(paragraph)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
        verify(movieService,times(1)).deleteMovie(anyInt());
    }
//    @Test
//    public void updateMovie() throws Exception {
//        paragraph.setComments("f");
//        when(movieService.updateMovie(anyInt(),any())).thenReturn(paragraph);
//        mockMvc.perform(MockMvcRequestBuilders.put("/paragraph-api/v1/paragraph/12")
//                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(paragraph)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//        verify(movieService,times(1)).updateMovie(anyInt(),any());
//    }
    @Test
    public void updateMovieFailure() throws Exception {
        when(movieService.updateMovie(anyInt(), (String) any())).thenThrow(MovieNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.put("/paragraph-api/v1/paragraph/12")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(paragraph)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
        verify(movieService,times(1)).updateMovie(anyInt(), (String) any());
    }
    @Test
    public void searchMovie() throws Exception {
        when(movieService.getMovieByTitle((String) any())).thenReturn(paragraph);
        mockMvc.perform(MockMvcRequestBuilders.post("/paragraph-api/v1/paragraph/la la")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(paragraph)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(movieService,times(1)).getMovieByTitle((String) any());
    }
    @Test
    public void searchMovieFailure() throws Exception {
        when(movieService.getMovieByTitle((String) any())).thenThrow(MovieNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/paragraph-api/v1/paragraph/la")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(paragraph)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
        verify(movieService,times(1)).getMovieByTitle((String) any());
    }

    private static String asJsonString(final Object obj)
    {
        try{
            return new ObjectMapper().writeValueAsString(obj);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

}
