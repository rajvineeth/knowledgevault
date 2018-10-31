package com.stackroute.paragraphsummarizationservice.exceptions;

import com.stackroute.paragraphsummarizationservice.domain.Paragraph;


public class MovieAlreadyExistsException extends Exception {
    public MovieAlreadyExistsException(Paragraph paragraph) {
        super(paragraph.getTitle()+" Paragraph already exists");
    }
}
