package com.stackroute.Crawlerservice.Controller;

import com.stackroute.Crawlerservice.Service.ExtractorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "home/cgi")
public class ExtractorController {

    @Autowired
    ExtractorServiceImpl service;

    public ExtractorController(ExtractorServiceImpl service) {
        this.service = service;
    }

    @GetMapping("{path}")
    public ResponseEntity<?> extractionFiles(@PathVariable("path") String path) {
        ResponseEntity responseEntity;
        service.getAllFiles(path);
        
    }
}
