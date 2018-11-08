package com.stackroute.Extractorservice.Service;

import com.stackroute.Extractorservice.Extractor.ExtractedFileData;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.microsoft.ooxml.OOXMLParser;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.parser.pkg.PackageParser;
import org.apache.tika.parser.txt.TXTParser;
import org.apache.tika.parser.xml.XMLParser;
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
    public ExtractedFileData extractOneFile(File file) throws IOException, TikaException, SAXException {

        FileInputStream inputStream = new FileInputStream(file);
        ExtractedFileData data = new ExtractedFileData();

        BodyContentHandler content = new BodyContentHandler(-1);
        ParseContext pcontext = new ParseContext();

        Metadata metadata = new Metadata();

        String backupContent = "";

        Tika tika = new Tika();
        String mediaType = tika.detect(inputStream);
        inputStream.close();

        FileInputStream newStream = new FileInputStream(file);

        switch (mediaType) {

            //PDF Parsing
            case "application/pdf":
                PDFParser pdfparser = new PDFParser();
                pdfparser.parse(newStream, content, metadata, pcontext);
                break;

            //Office Files Parsing
            case "application/x-tika-ooxml":
                OOXMLParser msofficeparser = new OOXMLParser();
                msofficeparser.parse(newStream, content, metadata, pcontext);
                break;

            //Package Parser
            case "application/zip":
                PackageParser packageparser = new PackageParser();
                packageparser.parse(newStream, content, metadata, pcontext);

                //JSON, Text and CSV Parsing
            case "text/plain":
                TXTParser txtParser = new TXTParser();
                txtParser.parse(newStream, content, metadata, pcontext);
                break;

            //XML Parsing
            case "application/xml":
                XMLParser xmlparser = new XMLParser();
                xmlparser.parse(newStream, content, metadata, pcontext);
                break;

            //Other Formats
            default:
                backupContent = tika.parseToString(file);
                tika.parse(newStream, metadata);
        }

        if (!backupContent.isEmpty())
            data.setContent(backupContent);
        else data.setContent(content.toString());

        data.setMetadata(String.valueOf(metadata));

        return data;

    }

}
