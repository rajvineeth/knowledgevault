package com.stackroute.Crawlerservice.Service;

//import jdk.internal.org.xml.sax.SAXException;
import com.stackroute.Crawlerservice.Extractor.FileExtractedData;
import org.apache.tika.exception.TikaException;

import java.io.*;
import java.util.List;

public interface ExtractorService {

    public List<File> getAllFiles(String path);

    public List<String> detectDocType(List<File> allFiles) throws IOException, TikaException;

    public FileExtractedData extractOneFile(File file) throws IOException, TikaException;

}
