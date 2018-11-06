package com.stackroute.Crawlerservice.Service;

//import jdk.internal.org.xml.sax.SAXException;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@Service
public class ExtractorServiceImpl implements ExtractorService {

    @Override
    public File[] getAllFiles(String path) {

        System.out.println("triggered");

        File directory = new File(path);

        List<File> list = Arrays.asList(directory.listFiles());

        for (File file: list) {

                if (file.isDirectory()) {

                    File[] subFolderFiles = file.listFiles();
                    list.remove(file);

                    for (File file1: subFolderFiles) {
                        list.add(file1);
                    }
                }
            }

        File[] allFiles = (File[]) list.toArray();
        return allFiles;
    }

    @Override
    public String detectDocTypeUsingFacade(InputStream stream)
            throws IOException {

        Tika tika = new Tika();
        String mediaType = tika.detect(stream);
        System.out.println(mediaType);
        return mediaType;
    }

    @Override
    public String extractContentUsingParser(InputStream stream) throws IOException, TikaException {

        Tika tika = new Tika();
        String content = tika.parseToString(stream);
        return content;
    }
}
