package com.stackroute.paragraphsummarizationservice.service;

import com.stackroute.paragraphsummarizationservice.domain.Paragraph;
import com.stackroute.paragraphsummarizationservice.exceptions.MovieAlreadyExistsException;
import com.stackroute.paragraphsummarizationservice.exceptions.MovieNotFoundException;
import com.stackroute.paragraphsummarizationservice.repository.ParagraphRepository;
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

public class ParagraphServiceTest {
    Paragraph paragraph;

    //Create a mock for UserRepository
    @Mock//test double
            ParagraphRepository paragraphRepository;

    //Inject the mocks as dependencies into UserServiceImpl
    @InjectMocks
    ParagraphServiceImpl movieService;



    List<Paragraph> list= null;


    @Before
    public void setUp(){
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        paragraph = new Paragraph();
        paragraph.setId(120);
        paragraph.setTitle("Rang De Basanti");
        paragraph.setComments("good paragraph");
        paragraph.setLanguage("Hindi");
        list = new ArrayList<>();
        list.add(paragraph);
    }

    @After
    public void tearDown() throws Exception {
        paragraphRepository.deleteAll();
    }

    @Test
    public void saveMovieTestSuccess() throws MovieAlreadyExistsException {
        when(paragraphRepository.save((Paragraph)any())).thenReturn(paragraph);
        Paragraph savedParagraph = movieService.addMovie(paragraph);
        Assert.assertEquals(paragraph, savedParagraph);

        //verify here verifies that userRepository save method is only called once
        verify(paragraphRepository,times(1)).save(paragraph);

    }

    @Test(expected = MovieAlreadyExistsException.class)
    public void saveMovieTestFailure() throws MovieAlreadyExistsException {
        when(paragraphRepository.save((Paragraph)any())).thenReturn(null);
        Paragraph savedParagraph = movieService.addMovie(paragraph);
        System.out.println("savedUser" + savedParagraph);
        Assert.assertEquals(paragraph, savedParagraph);
//add verify
        verify(movieService,times(1)).addMovie(paragraph);
        verify(paragraphRepository,times(1)).save(paragraph);
       //userService.saveUser(user);


    }

    @Test
    public void getAllMovies(){

        paragraphRepository.save(paragraph);
        //stubbing the mock to return specific data
        when(paragraphRepository.findAll()).thenReturn(list);
        List<Paragraph> movielist = movieService.getAllMovies();
        Assert.assertEquals(list,movielist);
        verify(paragraphRepository,times(1)).findAll();
        //add verify
    }

    @Test
    public void deleteMovie() throws MovieNotFoundException {
        when(paragraphRepository.existsById(paragraph.getId())).thenReturn(true);
        when(movieService.getMovieById(paragraph.getId())).thenReturn(java.util.Optional.ofNullable(paragraph));
        List<Paragraph> movielist = movieService.deleteMovie(paragraph.getId());
        List<Paragraph> list= paragraphRepository.findAll();
        Assert.assertEquals(list,movielist);
        verify(paragraphRepository,times(1)).existsById(paragraph.getId());
        verify(paragraphRepository,times(1)).delete(paragraph);
    }
    @Test(expected = MovieNotFoundException.class)
    public void deleteMovieFailure() throws MovieNotFoundException {
        when(paragraphRepository.existsById(paragraph.getId())).thenReturn(false);
        List<Paragraph> movielist = movieService.deleteMovie(paragraph.getId());
    }
    @Test
    public void updateMovie() throws MovieNotFoundException {
        when(paragraphRepository.save((Paragraph)any())).thenReturn(paragraph);
        when(paragraphRepository.existsById(paragraph.getId())).thenReturn(true);
        when(movieService.getMovieById(paragraph.getId())).thenReturn(java.util.Optional.ofNullable(paragraph));
        Paragraph updatedParagraph = movieService.updateMovie(120,"flop");
        Assert.assertEquals(paragraph.getComments(), updatedParagraph.getComments());
        verify(paragraphRepository,times(1)).existsById(paragraph.getId());
        verify(paragraphRepository,times(1)).save(paragraph);
    }
    @Test(expected = MovieNotFoundException.class)
    public void updateMovieFailure() throws MovieNotFoundException {
        when(paragraphRepository.existsById(paragraph.getId())).thenReturn(false);
        Paragraph updatedParagraph = movieService.updateMovie(120,"flop");
    }
    @Test
    public void searchMovie() throws MovieNotFoundException {
//        when(paragraphRepository.getMovieByTitle(anyString())).thenReturn(paragraph);
//        Paragraph updatedParagraph = movieService.getMovieByTitle("Rang De Basanti");
//        Assert.assertSame(paragraph, updatedParagraph);
//        verify(paragraphRepository,times(1)).getMovieByTitle(anyString());
    }
    @Test(expected = MovieNotFoundException.class)
    public void searchMovieFailure() throws MovieNotFoundException {
//        when(paragraphRepository.getMovieByTitle(anyString())).thenReturn(null);
//        Paragraph updatedParagraph = movieService.getMovieByTitle("sdc");
//        Assert.assertSame(null, updatedParagraph);
//        verify(paragraphRepository,times(1)).getMovieByTitle(anyString());
    }
}
