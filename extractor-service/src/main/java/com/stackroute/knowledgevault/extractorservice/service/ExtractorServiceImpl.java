package com.stackroute.knowledgevault.extractorservice.service;

import com.stackroute.knowledgevault.domain.ExtractedFileData;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExtractorServiceImpl implements ExtractorService {

    @Override
    /* Fetches all the files and files present inside the sub-folder, from the folder specified in the path */
    public List<File> getAllFiles(String path) {

        File directory = new File(path); //path = folder name (directory)

        File[] files = directory.listFiles();

        List<File> list = new ArrayList<>();

        assert files != null;
        for (File file: files) {

            if (file.isDirectory()) {

                File[] subFolderFiles = file.listFiles(); //Files inside the sub-folder

                assert subFolderFiles != null;
                for (File file1 : subFolderFiles) {
                    if (!file1.isDirectory()) {
                        list.add(file1); //appending sub-folder files to the original list
                    }
                }
            } else list.add(file);
        }

        return list;
    }

    @Override
    /* This method detects the document type of all the files in the list following the convention of apache tika. */
    public List<String> detectDocType(List<File> allFiles) throws IOException, TikaException {

        List<String> docTypes = new ArrayList<>(); //Document Type list

        Tika tika = new Tika();

        for (File file : allFiles) {
            FileInputStream inputstream = new FileInputStream(file);
            String mediaType = tika.detect(inputstream); //Detecting document type of the streaming file
            docTypes.add(mediaType);
            inputstream.close();
        }

        return docTypes;
    }

    /* This method simply extracts all the details about the file and populates the ExtractedFileData object based on the type of the document */
    @Override
    public ExtractedFileData extractOneFile(File file) throws IOException, TikaException, SAXException {

        ExtractedFileData data = new ExtractedFileData();

        BodyContentHandler content = new BodyContentHandler(-1); //writeLimit of -1 ensures parsing of large enough documents
        ParseContext pcontext = new ParseContext();

        Metadata metadata = new Metadata();

        /* This string will be used to hold the content of the input file in case it has a format different from the formats addressed here. */
        String backupContent = "";

        Tika tika = new Tika();
        FileInputStream inputStream = new FileInputStream(file);
        String mediaType = tika.detect(inputStream); //Detecting the document type
        inputStream.close();

        /* This stream will be used to parse the file */
        try (FileInputStream newStream = new FileInputStream(file)) {

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
                    break;

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
        }

        /* Setting up the properties of the ExtractedFileData object */
        if (!backupContent.isEmpty())
            data.setContent(backupContent);
        else data.setContent(content.toString());

        data.setMetadata(String.valueOf(metadata));

        return data;

    }
}