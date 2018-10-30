package com.stackroute.moviecruiserapp.exceptions;



public class MovieNotFoundException extends Exception {
    public MovieNotFoundException(String title) {
        super(title+" Movie not found");
    }
    public MovieNotFoundException(int id) {
        super(id+" Movie not found");
    }
}
