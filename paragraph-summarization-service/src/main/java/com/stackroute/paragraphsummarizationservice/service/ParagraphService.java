package com.stackroute.paragraphsummarizationservice.service;

import com.stackroute.paragraphsummarizationservice.domain.Paragraph;
import com.stackroute.paragraphsummarizationservice.exceptions.MovieAlreadyExistsException;
import com.stackroute.paragraphsummarizationservice.exceptions.MovieNotFoundException;


import java.util.List;
import java.util.Optional;

public interface ParagraphService {
    //@Query(value="select * from Paragraph",nativeQuery = true)
    public List<Paragraph> getAllMovies();
    //@Query(value="select * from Paragraph o where o.id=?1",nativeQuery = true)
    public Optional<Paragraph> getMovieById(int id) throws MovieNotFoundException;
    //@Query(value="select o from Paragraph o where o.title = :title",nativeQuery = true)
    public Paragraph getMovieByTitle(String title) throws MovieNotFoundException;
    //@Query(value="delete from Paragraph o where o=?1",nativeQuery = true)
    public List<Paragraph> deleteMovie(int id) throws MovieNotFoundException;
    public Paragraph updateMovie(int id, String comments) throws MovieNotFoundException;
    public Paragraph addMovie(Paragraph paragraph) throws MovieAlreadyExistsException;
}
