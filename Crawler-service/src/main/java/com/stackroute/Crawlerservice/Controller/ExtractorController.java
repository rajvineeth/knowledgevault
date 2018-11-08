package com.stackroute.Crawlerservice.Controller;

import com.stackroute.Crawlerservice.Extractor.FileExtractedData;
import com.stackroute.Crawlerservice.Service.ExtractorService;
import org.apache.tika.exception.TikaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "home/cgi")
public class ExtractorController {

    @Autowired
    ExtractorService service;

    public ExtractorController(ExtractorService service) {
        this.service = service;
    }

    @GetMapping("{path}")
    public ResponseEntity<?> displayAllFiles(@PathVariable("path") String path) {

        List<File> allFiles = service.getAllFiles("/home/cgi/" + path);

        ResponseEntity responseEntity = new ResponseEntity<List<File>>(allFiles, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping(value = "{path}/extract")
    public ResponseEntity<?> extractAllFiles(@PathVariable("path") String path) {

        ResponseEntity responseEntity = null;
        //System.out.println("path");
        List<File> allFiles = service.getAllFiles("/home/cgi/" + path);

        try {
            for (File instance : allFiles) {
                    FileExtractedData data = service.extractOneFile(instance);
                    String metadata = data.getMetadata();
                    Object content = data.getContent();
                    responseEntity = new ResponseEntity<String>("Details Sent", HttpStatus.OK);
            }
        }
        catch (IOException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (TikaException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }

        return responseEntity;
    }

    @GetMapping("{path}/docTypes")
    public ResponseEntity<?> displayDocTypes(@PathVariable("path") String path) {

        ResponseEntity responseEntity;
        List<File> allFiles = service.getAllFiles("/home/cgi/" + path);
        List<String> docTypes = null;
        try {
            docTypes = service.detectDocType(allFiles);
            responseEntity = new ResponseEntity<List<String>>(docTypes, HttpStatus.OK);
        } catch (IOException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (TikaException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }

        return responseEntity;
    }

    @GetMapping("{path}/extract/{file}")
    public ResponseEntity<?> extractFile(@PathVariable("path") String path, @PathVariable("file") File file) {

        FileExtractedData data;
        ResponseEntity responseEntity = null;
        List<File> allFiles = service.getAllFiles("/home/cgi/" + path);

        try {
            for (File instance : allFiles) {
                if (instance.getName().equals(file.getName())) {
                    data = service.extractOneFile(instance);
                    System.out.println(data.getContent());
                    responseEntity = new ResponseEntity<FileExtractedData>(data, HttpStatus.OK);
                    break;
                }

            }
        }
        catch (IOException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (TikaException e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
        }

        return responseEntity;
    }

}
