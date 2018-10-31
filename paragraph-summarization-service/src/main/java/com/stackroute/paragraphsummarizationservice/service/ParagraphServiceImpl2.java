package com.stackroute.paragraphsummarizationservice.service;

import com.stackroute.paragraphsummarizationservice.domain.Paragraph;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Qualifier("impl2")
public class ParagraphServiceImpl2 implements ParagraphService {
    @Override
    public List<Paragraph> getAllMovies() {
        System.out.println("Retrieving movies from aws");
        return null;
    }

    @Override
    public Optional<Paragraph> getMovieById(int id) {
        System.out.println(id);
        return null;
    }

    @Override
    public Paragraph getMovieByTitle(String title) {
        System.out.println(title);
        return null;
    }

    @Override
    public List<Paragraph> deleteMovie(int id) {
        System.out.println(id);
        return null;
    }

    @Override
    public Paragraph updateMovie(int id, String comments) {
        System.out.println(id);
        return null;
    }

    @Override
    public Paragraph addMovie(Paragraph paragraph) {
        System.out.println(paragraph);
        return paragraph;
    }
}
