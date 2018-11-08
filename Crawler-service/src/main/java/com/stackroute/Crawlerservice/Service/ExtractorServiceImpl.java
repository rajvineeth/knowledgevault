package com.stackroute.Crawlerservice.Service;

import com.stackroute.Crawlerservice.Extractor.FileExtractedData;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ExtractorServiceImpl implements ExtractorService {

    @Override
    public List<File> getAllFiles(String path) {

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

        return list;
    }

    @Override
    public List<String> detectDocType(List<File> allFiles) throws IOException, TikaException {

        List<String> docTypes = new ArrayList<String>();
        Tika tika = new Tika();

        for (File file : allFiles) {
            FileInputStream inputstream = new FileInputStream(file);
            String mediaType = tika.detect(inputstream);
            docTypes.add(mediaType);
        }

        return docTypes;
    }

    @Override
    public FileExtractedData extractOneFile(File file) throws IOException, TikaException {

        FileInputStream inputStream = new FileInputStream(file);
        FileExtractedData data = new FileExtractedData();
        BodyContentHandler handler = new BodyContentHandler(-1);
        ParseContext pcontext = new ParseContext();

//        Tika tika = new Tika();
        Metadata metadata = new Metadata();
//        tika.parse(inputStream, metadata);

        //parsing the document using PDF parser
        PDFParser pdfparser = new PDFParser();
        try {
            pdfparser.parse(inputStream, handler, metadata, pcontext);
        } catch (SAXException e) {
            e.printStackTrace();
        }

        data.setMetadata(String.valueOf(metadata));
//        data.setContent(tika.parseToString(file));
        data.setContent(handler.toString());

        return data;

    }

}
