package com.stackroute.paragraphsummarizationservice.controller;

import com.stackroute.paragraphsummarizationservice.domain.Paragraph;
import com.stackroute.paragraphsummarizationservice.exceptions.MovieAlreadyExistsException;
import com.stackroute.paragraphsummarizationservice.exceptions.MovieNotFoundException;
import com.stackroute.paragraphsummarizationservice.service.ParagraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
@RequestMapping(value="/paragraph-api/v1")
public class ParagraphController {
    private ParagraphService paragraphService;
    @Autowired
    public ParagraphController(ParagraphService paragraphService) {
        this.paragraphService = paragraphService;
    }
    @Value("${paragraph-service.controller.exceptionmsg1}")
    private String exceptionMessage;
    @Value("${paragraph-service.controller.exceptionmsg2}")
    private String exceptionMessageExists;
    @PostMapping(value="/paragraph")
    public ResponseEntity<?> saveParagraph(@RequestBody @Valid Paragraph paragraph){
        ResponseEntity responseEntity;
        try {
            Paragraph savedParagraph = paragraphService.addMovie(paragraph);
            responseEntity = new ResponseEntity<Paragraph>(savedParagraph, HttpStatus.CREATED);
        }
        catch (MovieAlreadyExistsException ex){
            responseEntity = new ResponseEntity<String>(exceptionMessageExists,HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    @GetMapping(value="/paragraph")
    public ResponseEntity<?> getAllMovies(){
        List<Paragraph> paragraphList = paragraphService.getAllMovies();
        return new ResponseEntity<List<Paragraph>>(paragraphList,HttpStatus.OK);
    }
    @PostMapping(value="/paragraph/{title}")
    public ResponseEntity<?> getMovieByTitle(@Valid @PathVariable(value="title") String title) {
        ResponseEntity responseEntity;
        try {
            Paragraph savedParagraph = paragraphService.getMovieByTitle(title);
            responseEntity = new ResponseEntity<Paragraph>(savedParagraph, HttpStatus.OK);
        }
        catch (MovieNotFoundException ex){
            responseEntity = new ResponseEntity<String>(exceptionMessage,HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    @DeleteMapping(value="/paragraph/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable(value="id") int paragraphId){
        ResponseEntity responseEntity;
        try {
            List<Paragraph> savedList = paragraphService.deleteMovie(paragraphId);
            responseEntity = new ResponseEntity<List<Paragraph>>(savedList, HttpStatus.OK);
        }
        catch (MovieNotFoundException ex){
            responseEntity = new ResponseEntity<String>(exceptionMessage,HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    @PutMapping(value = "/paragraph/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable(value="id") int paragraphId,@Valid @RequestBody String comments){
        ResponseEntity responseEntity;
        try {
            Paragraph savedParagraph = paragraphService.updateMovie(paragraphId,comments);
            responseEntity = new ResponseEntity<Paragraph>(savedParagraph, HttpStatus.OK);
        }
        catch (MovieNotFoundException ex){
            responseEntity = new ResponseEntity<String>(exceptionMessage,HttpStatus.CONFLICT);
        }
        return responseEntity;
    }


}
