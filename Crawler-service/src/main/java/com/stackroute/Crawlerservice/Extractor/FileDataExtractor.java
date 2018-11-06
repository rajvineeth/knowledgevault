package com.stackroute.Crawlerservice.Extractor;

import com.stackroute.Crawlerservice.Service.ExtractorServiceImpl;
//import javafx.beans.property.SimpleObjectProperty;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;


public class FileDataExtractor {

    File[] allFiles;

    @Autowired
    ExtractorServiceImpl service;

    public void setFileArray(String path) {
        //System.out.println("triggered");
       this.allFiles = service.getAllFiles(path);
    }

    public void documentType() throws IOException {
        for (File file : allFiles) {
            FileInputStream inputstream = new FileInputStream(file);
            service.detectDocTypeUsingFacade(inputstream);
        }
    }

    public static void main(String[] args) throws IOException {
        FileDataExtractor extractor = new FileDataExtractor();
        extractor.setFileArray("/home/cgi/Pictures");
        extractor.documentType();
    }
}