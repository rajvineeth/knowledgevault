package com.stackroute.service;

import com.stackroute.domain.ExtractedFileData;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import java.io.*;
import java.util.List;

public interface ExtractorService {

    public List<File> getAllFiles(String path);

    public List<String> detectDocType(List<File> allFiles) throws IOException, TikaException;

    public ExtractedFileData extractOneFile(File file) throws IOException, TikaException, SAXException;

}