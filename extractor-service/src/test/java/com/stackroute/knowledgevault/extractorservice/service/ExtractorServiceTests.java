package com.stackroute.knowledgevault.extractorservice.service;

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
    private ExtractedFileData data = new ExtractedFileData();

    @Autowired
    private ExtractorService extractorService;

    @Before
    public void setUp() {
        files.add(new File("./src/main/resources/Doc/fakie/Cholera.txt"));
        files.add(new File("./src/main/resources/Doc/fakie/Typhoid.txt"));
        files.add(new File("./src/main/resources/Doc/fakie/Malaria.txt"));
        files.add(new File("./src/main/resources/Doc/HIV.ppt"));

        files.add(new File("./src/main/resources/Doc/Cholera.txt"));
        files.add(new File("./src/main/resources/Doc/new/Tuberculosis.json"));
        files.add(new File("./src/main/resources/Doc/new/Typhoid.txt"));
        files.add(new File("./src/main/resources/Doc/new/gastro.pdf"));
        files.add(new File("./src/main/resources/Doc/gastro.pdf"));

        files.add(new File("./src/main/resources/Doc/Malaria.txt"));

        data.setMetadata("Content-Encoding=UTF-8 Content-Type=text/plain; charset=UTF-8");
        data.setContent("Malaria is a life-threatening disease. It’s typically transmitted through the bite of an infected Anopheles mosquito. Infected mosquitoes carry the Plasmodium parasite. When this mosquito bites you, the parasite is released into your bloodstream.\\n\\nOnce the parasites are inside your body, they travel to the liver, where they mature. After several days, the mature parasites enter the bloodstream and begin to infect red blood cells.\\n\\nWithin 48 to 72 hours, the parasites inside the red blood cells multiply, causing the infected cells to burst open.\\n\\nThe parasites continue to infect red blood cells, resulting in symptoms that occur in cycles that last two to three days at a time.\\n\\nMalaria\\n\\n    Causes\\n    Symptoms\\n    Diagnosis\\n    Complications\\n    Treatment\\n    Outlook\\n    Prevention\\n\\nWhat is malaria?\\n\\nMalaria is a life-threatening disease. It’s typically transmitted through the bite of an infected Anopheles mosquito. Infected mosquitoes carry the Plasmodium parasite. When this mosquito bites you, the parasite is released into your bloodstream.\\n\\nOnce the parasites are inside your body, they travel to the liver, where they mature. After several days, the mature parasites enter the bloodstream and begin to infect red blood cells.\\n\\nWithin 48 to 72 hours, the parasites inside the red blood cells multiply, causing the infected cells to burst open.\\n\\nThe parasites continue to infect red blood cells, resulting in symptoms that occur in cycles that last two to three days at a time.\\n\\nMalaria is typically found in tropical and subtropical climates where the parasites can live. The World Health Organization (WHO) states that, in 2016, there were an estimated 216 million cases of malaria in 91 countries.\\n\\nIn the United States, the Centers for Disease Control and Prevention (CDC) report 1,700 cases of malaria annually. Most cases of malaria develop in people who travel to countries where malaria is more common.\\n\\nRead more: Learn about the relationship between cytopenia and malaria »\\nWhat causes malaria?\\n\\nMalaria can occur if a mosquito infected with the Plasmodium parasite bites you. There are four kinds of malaria parasites that can infect humans: Plasmodium vivax, P. ovale, P. malariae, and P. falciparum.\\n\\nP. falciparum causes a more severe form of the disease and those who contract this form of malaria have a higher risk of death. An infected mother can also pass the disease to her baby at birth. This is known as congenital malaria.\\n\\nMalaria is transmitted by blood, so it can also be transmitted through:\\n\\n    an organ transplant\\n    a transfusion\\n    use of shared needles or syringes\\n\\nWhat are the symptoms of malaria?\\n\\nThe symptoms of malaria typically develop within 10 days to 4 weeks following the infection. In some cases, symptoms may not develop for several months. Some malarial parasites can enter the body but will be dormant for long periods of time.\\n\\nCommon symptoms of malaria include:\\n\\n    shaking chills that can range from moderate to severe\\n    high fever\\n    profuse sweating\\n    headache\\n    nausea\\n    vomiting\\n    abdominal pain\\n    diarrhea\\n    anemia\\n    muscle pain\\n    convulsions\\n    coma\\n    bloody stools\\n\\nMalaria can cause a number of life-threatening complications. The following may occur:\\n\\n    swelling of the blood vessels of the brain, or cerebral malaria\\n    an accumulation of fluid in the lungs that causes breathing problems, or pulmonary edema\\n    organ failure of the kidneys, liver, or spleen\\n    anemia due to the destruction of red blood cells\\n    low blood sugar\n\n\n");
    }

    @Test
    public void testGetAllFiles() {

        List<File> allFiles = extractorService.getAllFiles("./src/main/resources/Doc");

        Assert.assertEquals(files, allFiles);
        Assert.assertNotNull(allFiles);

        files.clear();
        files.add(new File("./src/main/resources/Doc/new/Tuberculosis.json"));
        files.add(new File("./src/main/resources/Doc/new/Typhoid.txt"));
        files.add(new File("./src/main/resources/Doc/new/gastro.pdf"));

        allFiles.clear();
        allFiles = extractorService.getAllFiles("./src/main/resources/Doc/new");

        Assert.assertEquals(files, allFiles);
    }

    @Test
    public void testDetectDocType() throws IOException, TikaException {

        files.clear();
        files.add(new File("./src/main/resources/Doc/new/Tuberculosis.json"));
        files.add(new File("./src/main/resources/Doc/new/gastro.pdf"));
        files.add(new File("./src/main/resources/Doc/new/Typhoid.txt"));

        types.add("text/plain");
        types.add("application/pdf");
        types.add("text/plain");

        List<String> docTypes = extractorService.detectDocType(files);

        Assert.assertEquals(types, docTypes);
        Assert.assertNotNull(docTypes);

        types.clear();
        types.add("json");
        types.add("pdf");
        types.add("txt");

        Assert.assertNotEquals(types, docTypes);
    }

    @Test(expected = IOException.class)
    public void testDetectDocTypeFailure() throws IOException, TikaException {
        files.clear();

        files.add(new File("./src/main/resources/Doc/new/gastro.pdf"));
        files.add(new File("./src/main/resources/Doc"));

        List<String> docTypes = extractorService.detectDocType(files);

        System.out.println("Found a folder while iterating.");
    }

//    @Test
//    public void testExtractOneFile() throws TikaException, IOException, SAXException {
//
//        ExtractedFileData extractedData = extractorService.extractOneFile(new File("./src/main/resources/Doc/fakie/Malaria.txt"));
//        data.setId(extractedData.getId());
//
//        Assert.assertEquals(data, extractedData);
//        Assert.assertNotNull(extractorService.extractOneFile(new File("./src/main/resources/Doc/new/Tuberculosis.json")));
//        Assert.assertNotEquals(data.getId(), extractorService.extractOneFile(new File("./src/main/resources/Doc/new/Tuberculosis.json")).getId());
//    }

    @Test(expected = IOException.class)
    public void testExtractOneFileFailure() throws TikaException, IOException, SAXException {

        ExtractedFileData extractedData = extractorService.extractOneFile(new File("./src/main/resources/Doc/new"));
        data.setId(extractedData.getId());

        System.out.println("Cannot parse a folder.");
        Assert.assertNotEquals(data.getId(), extractedData.getId());
    }
}