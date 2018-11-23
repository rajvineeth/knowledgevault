package com.stackroute.knowledgevault.paragraphtokenizer.service;

import com.stackroute.knowledgevault.domain.Document;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ParaTokenizerImplTest {

//    @Value("${tokenizer_test_folderPath}")
    private String folder_path= "src/main/resources/";

    private static final Logger LOGGER = LoggerFactory.getLogger(ParaTokenizerImplTest.class);

    ParaTokenizer paraTokenizer;
    @Before
    public void setUp() throws Exception {
        paraTokenizer = new ParaTokenizerImpl();
    }

    @After
    public void tearDown() throws Exception {
        paraTokenizer = null;
    }

    @Test
    public void tokenizePara() throws IOException {
        String testPath = folder_path + "forTest";
        String txt = FileUtils.readFileToString(new File(testPath),"UTF-8");
        String[] adsd = txt.trim().split("\\n+");
        Integer id = 21;
        Document doc = new Document(id,txt);

        List expList = new ArrayList();
        for (int i = 0; i < adsd.length; i++){

            LOGGER.info(adsd[i]);
            expList.add(new Document(id,adsd[i]));
        }
        LOGGER.info(expList.toString());
        List list = paraTokenizer.tokenizePara(doc);

        assertEquals(expList,list);
    }

    @Test
    public void tokenizePara2() throws IOException {
        String testPath = folder_path + "forTest2";
        String txt = FileUtils.readFileToString(new File(testPath),"UTF-8");
        String[] adsd = txt.trim().split("\\n+");
        Integer id = 21;
        Document doc = new Document(id,txt);

        List expList = new ArrayList();
        for (int i = 0; i < adsd.length; i++){

            LOGGER.info(adsd[i]);
            expList.add(new Document(id,adsd[i]));
        }
        LOGGER.info(expList.toString());
        List list = paraTokenizer.tokenizePara(doc);

        assertEquals(expList,list);
    }

    @Test
    public void tokenizePara3() throws IOException {
        String testPath = folder_path + "forTest3";
        String txt = FileUtils.readFileToString(new File(testPath),"UTF-8");
        String[] adsd = txt.trim().split("\\n+");
        Integer id = 21;
        Document doc = new Document(id,txt);

        List expList = new ArrayList();
        for (int i = 0; i < adsd.length; i++){

            LOGGER.info(adsd[i]);
            expList.add(new Document(id,adsd[i]));
        }
        LOGGER.info(expList.toString());
        List list = paraTokenizer.tokenizePara(doc);

        assertEquals(expList,list);
    }
}