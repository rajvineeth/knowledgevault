package com.stackroute.Crawlerservice.Service;

//import jdk.internal.org.xml.sax.SAXException;
import org.apache.tika.exception.TikaException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface ExtractorService {

    public File[] getAllFiles(String path);

    public String detectDocTypeUsingFacade(InputStream stream) throws IOException;

    public String extractContentUsingParser(InputStream stream) throws IOException, TikaException;
}
