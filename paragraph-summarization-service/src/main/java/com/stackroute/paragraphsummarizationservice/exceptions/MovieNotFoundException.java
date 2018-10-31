package com.stackroute.paragraphsummarizationservice.exceptions;



public class MovieNotFoundException extends Exception {
    public MovieNotFoundException(String title) {
        super(title+" Paragraph not found");
    }
    public MovieNotFoundException(int id) {
        super(id+" Paragraph not found");
    }
}
