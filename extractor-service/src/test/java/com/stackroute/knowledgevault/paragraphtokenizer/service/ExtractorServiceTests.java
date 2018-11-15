package com.stackroute.knowledgevault.paragraphtokenizer.service;

import com.stackroute.knowledgevault.domain.ExtractedFileData;
import org.apache.tika.exception.TikaException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExtractorServiceTests {

    private List<File> files = new ArrayList<>();
    private List<String> types = new ArrayList<>();
    ExtractedFileData data = new ExtractedFileData();

    @Autowired
    ExtractorService extractorService;

    @Before
    public void setUp() {
        files.add(new File("/home/cgi/fakie/fakie/README.md"));
        files.add(new File("/home/cgi/fakie/fakie/JavaScript_Evaluation_Exercise_1.odt"));
        files.add(new File("/home/cgi/fakie/fakie/Untitled Document.MD"));
        files.add(new File("/home/cgi/fakie/README.md"));
        files.add(new File("/home/cgi/fakie/Profile Update Process.pptx"));
        files.add(new File("/home/cgi/fakie/Untitled Document.MD"));
        files.add(new File("/home/cgi/fakie/new/CGI-Reimbursement Mail.pdf.pdf"));
        files.add(new File("/home/cgi/fakie/new/package.json"));
        files.add(new File("/home/cgi/fakie/new/JavaScript_Evaluation_Exercise_1.odt"));

        data.setMetadata("Content-Encoding=ISO-8859-1 Content-Type=text/plain; charset=ISO-8859-1");
        data.setContent("# fakie\n\n");
    }

    @Test
    public void testGetAllFiles() {

        List<File> allFiles = extractorService.getAllFiles("/home/cgi/fakie");

        Assert.assertEquals(files, allFiles);
        Assert.assertNotNull(allFiles);

        files.clear();
        files.add(new File("/home/cgi/fakie/new/CGI-Reimbursement Mail.pdf.pdf"));
        files.add(new File("/home/cgi/fakie/new/package.json"));
        files.add(new File("/home/cgi/fakie/new/JavaScript_Evaluation_Exercise_1.odt"));

        allFiles.clear();
        allFiles = extractorService.getAllFiles("/home/cgi/fakie/new");

        Assert.assertEquals(files, allFiles);
    }

    @Test
    public void testDetectDocType() throws IOException, TikaException {

        files.clear();
        files.add(new File("/home/cgi/fakie/new/CGI-Reimbursement Mail.pdf.pdf"));
        files.add(new File("/home/cgi/fakie/new/package.json"));
        files.add(new File("/home/cgi/fakie/new/JavaScript_Evaluation_Exercise_1.odt"));

        types.add("application/pdf");
        types.add("text/plain");
        types.add("application/zip");

        List<String> docTypes = extractorService.detectDocType(files);

        Assert.assertEquals(types, docTypes);
        Assert.assertNotNull(docTypes);

        types.clear();
        types.add("pdf");
        types.add("json");
        types.add("odt");

        Assert.assertNotEquals(types, docTypes);
    }

    @Test(expected = IOException.class)
    public void testDetectDocTypeFailure() throws IOException, TikaException {
        files.clear();

        files.add(new File("/home/cgi/fakie/new/CGI-Reimbursement Mail.pdf.pdf"));
        files.add(new File("/home/cgi/fakie"));

        List<String> docTypes = extractorService.detectDocType(files);

        System.out.println("Found a folder while iterating.");
    }

    @Test
    public void testExtractOneFile() throws TikaException, IOException, SAXException {

        ExtractedFileData extractedData = extractorService.extractOneFile(new File("/home/cgi/fakie/README.md"));
        data.setId(extractedData.getId());

        Assert.assertEquals(data, extractedData);
        Assert.assertNotNull(extractorService.extractOneFile(new File("/home/cgi/fakie/new/package.json")));
        Assert.assertNotEquals(data.getId(), extractorService.extractOneFile(new File("/home/cgi/fakie/new/package.json")).getId());
    }

    @Test(expected = IOException.class)
    public void testExtractOneFileFailure() throws TikaException, IOException, SAXException {

        ExtractedFileData extractedData = extractorService.extractOneFile(new File("/home/cgi/fakie/new"));
        data.setId(extractedData.getId());

        System.out.println("Cannot parse a folder.");
        Assert.assertNotEquals(data.getId(), extractedData.getId());
    }
}