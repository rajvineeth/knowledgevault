package com.stackroute.moviecruiserapp.exceptions;

import com.stackroute.moviecruiserapp.domain.Movie;


public class MovieAlreadyExistsException extends Exception {
    public MovieAlreadyExistsException(Movie movie) {
        super(movie.getTitle()+" Movie already exists");
    }
}
