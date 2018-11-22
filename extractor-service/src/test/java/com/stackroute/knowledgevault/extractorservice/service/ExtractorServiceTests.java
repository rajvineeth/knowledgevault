package com.stackroute.knowledgevault.extractorservice.service;

import com.stackroute.knowledgevault.domain.ExtractedFileData;
import org.apache.tika.exception.TikaException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    private ExtractedFileData data = new ExtractedFileData();

    @Autowired
    private ExtractorService extractorService;

    @Value("${folder.test_path}")
    private String path;

    @Value("${folder.sub_folder}")
    private String sub_folder_path;

    @Value("${folder.doc_type_message}")
    private String detected_folder_message;

    @Value("${folder.parse_message}")
    private String parse_folder_message;

    @Value("${README.metadata}")
    private String metadata;

    @Before
    public void setUp() {
        files.add(new File(path + "/from23final.pdf"));
        files.add(new File(path + "/fakie/README.md"));
        files.add(new File(path + "/fakie/JavaScript_Evaluation_Exercise_1.odt"));
        files.add(new File(path + "/fakie/Untitled Document.MD"));
        files.add(new File(path + "/README.md"));
        files.add(new File(path + "/Profile Update Process.pptx"));
        files.add(new File(path + "/Untitled Document.MD"));
        files.add(new File(path + "/new/CGI-Reimbursement Mail.pdf.pdf"));
        files.add(new File(path + "/new/package.json"));
        files.add(new File(path + "/new/JavaScript_Evaluation_Exercise_1.odt"));

        data.setMetadata(metadata);
        data.setContent("# fakie\n\n");
    }

    @Test
    public void testGetAllFiles() {

        List<File> allFiles = extractorService.getAllFiles(path);

        Assert.assertEquals(files, allFiles);
        Assert.assertNotNull(allFiles);

        files.clear();
        files.add(new File(sub_folder_path + "/CGI-Reimbursement Mail.pdf.pdf"));
        files.add(new File(sub_folder_path + "/package.json"));
        files.add(new File(sub_folder_path + "/JavaScript_Evaluation_Exercise_1.odt"));

        allFiles.clear();
        allFiles = extractorService.getAllFiles(sub_folder_path);

        Assert.assertEquals(files, allFiles);
    }

    @Test
    public void testDetectDocType() throws IOException, TikaException {

        files.clear();
        files.add(new File(sub_folder_path + "/package.json"));
        files.add(new File(sub_folder_path + "/CGI-Reimbursement Mail.pdf.pdf"));
        files.add(new File(sub_folder_path + "/JavaScript_Evaluation_Exercise_1.odt"));

        types.add("text/plain");
        types.add("application/pdf");
        types.add("application/zip");

        List<String> docTypes = extractorService.detectDocType(files);

        Assert.assertEquals(types, docTypes);
        Assert.assertNotNull(docTypes);

        types.clear();
        types.add("json");
        types.add("pdf");
        types.add("odt");

        Assert.assertNotEquals(types, docTypes);
    }

    @Test(expected = IOException.class)
    public void testDetectDocTypeFailure() throws IOException, TikaException {
        files.clear();

        files.add(new File(sub_folder_path + "/CGI-Reimbursement Mail.pdf.pdf"));
        files.add(new File(path));

        List<String> docTypes = extractorService.detectDocType(files);

        System.out.println(detected_folder_message);
    }

    @Test
    public void testExtractOneFile() throws TikaException, IOException, SAXException {

        ExtractedFileData extractedData = extractorService.extractOneFile(new File(path + "/fakie/README.md"));
        data.setId(extractedData.getId());

        Assert.assertEquals(data, extractedData);
        Assert.assertNotNull(extractorService.extractOneFile(new File(sub_folder_path + "/package.json")));
        Assert.assertNotEquals(data.getId(), extractorService.extractOneFile(new File(sub_folder_path + "/package.json")).getId());
    }

    @Test(expected = IOException.class)
    public void testExtractOneFileFailure() throws TikaException, IOException, SAXException {

        ExtractedFileData extractedData = extractorService.extractOneFile(new File(path));
        data.setId(extractedData.getId());

        System.out.println(parse_folder_message);
        Assert.assertNotEquals(data.getId(), extractedData.getId());
    }
}