package com.stackroute.paragraphsummarizationservice.service;

import com.stackroute.paragraphsummarizationservice.domain.Paragraph;
import com.stackroute.paragraphsummarizationservice.exceptions.MovieAlreadyExistsException;
import com.stackroute.paragraphsummarizationservice.exceptions.MovieNotFoundException;
import com.stackroute.paragraphsummarizationservice.repository.ParagraphRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Qualifier("impl1")
@Primary
public class ParagraphServiceImpl implements ParagraphService {

    ParagraphRepository paragraphRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(ParagraphServiceImpl.class);

    public ParagraphServiceImpl(ParagraphRepository paragraphRepository) {
        this.paragraphRepository = paragraphRepository;
    }

    public List<Paragraph> getAllMovies() {
        return (List<Paragraph>) paragraphRepository.findAll();
    }


    public Optional<Paragraph> getMovieById(int id)  {
        Optional<Paragraph> movie;
        movie= paragraphRepository.findById(id);
        return movie;
    }

    public Paragraph getMovieByTitle(String title) throws MovieNotFoundException {
//        Optional<Paragraph> movie;
//        List<Paragraph> movieList=getAllMovies();
//        for(Paragraph movie2:movieList){
//            if(movie2.getTitle().equals(title))
//                return movie2;
//        }
//        Paragraph savedParagraph = paragraphRepository.getMovieByTitle(title);
//        if(savedParagraph == null){
//            LOGGER.warn("Paragraph not found exception - {}", title);
//            throw new MovieNotFoundException(title);
//        }
        return new Paragraph();
       // throw new MovieNotFoundException(title);
    }



    public List<Paragraph> deleteMovie(int id) throws MovieNotFoundException {
        if(!paragraphRepository.existsById(id))
            throw new MovieNotFoundException(id);
        Optional<Paragraph> movie1=getMovieById(id);
        paragraphRepository.delete(movie1.get());
        return getAllMovies();
    }


    public Paragraph updateMovie(int id, String comments) throws MovieNotFoundException {

        if(!paragraphRepository.existsById(id))
            throw new MovieNotFoundException(id);
        Optional<Paragraph> movie1=getMovieById(id);
        movie1.get().setComments(comments);
        Paragraph savedParagraph = paragraphRepository.save(movie1.get());
        return savedParagraph;
    }


    public Paragraph addMovie(Paragraph paragraph) throws MovieAlreadyExistsException {
        if(paragraphRepository.existsById(paragraph.getId()))
            throw new MovieAlreadyExistsException(paragraph);
//        for(Paragraph movie2:movieList){
//            if(movie2.getId()==(paragraph.getId()))
//                throw new MovieAlreadyExistsException(paragraph);
//        }
        Paragraph savedParagraph = paragraphRepository.save(paragraph);
        if(savedParagraph == null){
            LOGGER.warn("Paragraph already exists exception ");
            throw new MovieAlreadyExistsException(paragraph);
        }
        return savedParagraph;
    }
}
