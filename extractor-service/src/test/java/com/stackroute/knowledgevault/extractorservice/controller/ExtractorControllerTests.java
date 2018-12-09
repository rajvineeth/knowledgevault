package com.stackroute.knowledgevault.extractorservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.knowledgevault.domain.ExtractedFileData;
import com.stackroute.knowledgevault.extractorservice.service.ExtractorService;
import org.apache.tika.exception.TikaException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ExtractorControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExtractorService extractorService;

    @MockBean
    private KafkaTemplate<String, ExtractedFileData> kafkaTemplate;

    @InjectMocks
    private ExtractorController controller;

    private List<File> files = new ArrayList<>();
    private ExtractedFileData data = new ExtractedFileData();
    private List<String> docTypes = new ArrayList<>();

    @Value("${Map.all_files}")
    private String files_path;

    @Value("${Map.extract_all_files}")
    private String extraction_path;

    @Value("${Map.doc_types}")
    private String doc_path;

    @Value("${Map.extract_one_file}")
    private String extraction_file_path;

    @Before
    public void Setup() {
        files.add(new File("README.md"));
        files.add(new File("JavaScript_Evaluation_Exercise_1.odt"));
        files.add(new File("Untitled Document.MD"));
        files.add(new File("CGI-Reimbursement Mail.pdf.pdf"));
        files.add(new File("package.json"));
        files.add(new File("Profile Update Process.pptx"));

        docTypes.add("text/plain");
        docTypes.add("application/x-tika-ooxml");
    }
    @Test
    public void displayAllFiles() throws Exception {
        when(extractorService.getAllFiles(anyString())).thenReturn(files);
        mockMvc.perform(MockMvcRequestBuilders.get(files_path)
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(files)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void extractAllFiles() throws Exception {
        when(extractorService.getAllFiles(anyString())).thenReturn(files);

        when(extractorService.extractOneFile((File) any())).thenReturn(data);
        mockMvc.perform(MockMvcRequestBuilders.get(extraction_path)
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(files)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testExtractAllFilesIOFailure() throws Exception {
        when(extractorService.getAllFiles(anyString())).thenReturn(files);

        when(extractorService.extractOneFile((File) any())).thenThrow(IOException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/Doc/extract/CGI-Reimbursement Mail.pdf.pdf")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(files)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testExtractAllFilesTikaFailure() throws Exception {
        when(extractorService.getAllFiles(anyString())).thenReturn(files);

        when(extractorService.extractOneFile((File) any())).thenThrow(TikaException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/Doc/extract/CGI-Reimbursement Mail.pdf.pdf")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(files)))
                .andExpect(MockMvcResultMatchers.status().isExpectationFailed())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testExtractAllFilesSAXFailure() throws Exception {
        when(extractorService.getAllFiles(anyString())).thenReturn(files);

        when(extractorService.extractOneFile((File) any())).thenThrow(SAXException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/Doc/extract/CGI-Reimbursement Mail.pdf.pdf")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(files)))
                .andExpect(MockMvcResultMatchers.status().isExpectationFailed())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testDisplayDocTypes() throws Exception {
        when(extractorService.getAllFiles(anyString())).thenReturn(files);

        when(extractorService.detectDocType(ArgumentMatchers.<File>anyList())).thenReturn(docTypes);
        mockMvc.perform(MockMvcRequestBuilders.get(doc_path)
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(files)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testDisplayDocTypesIOFailure() throws Exception {
        when(extractorService.getAllFiles(anyString())).thenReturn(files);

        when(extractorService.detectDocType(ArgumentMatchers.<File>anyList())).thenThrow(IOException.class);
        mockMvc.perform(MockMvcRequestBuilders.get(doc_path)
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(files)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testDisplayDocTypesTikaFailure() throws Exception {
        when(extractorService.getAllFiles(anyString())).thenReturn(files);

        when(extractorService.detectDocType(ArgumentMatchers.<File>anyList())).thenThrow(TikaException.class);
        mockMvc.perform(MockMvcRequestBuilders.get(doc_path)
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(files)))
                .andExpect(MockMvcResultMatchers.status().isExpectationFailed())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testExtractFile() throws Exception {
        when(extractorService.getAllFiles(anyString())).thenReturn(files);

        when(extractorService.extractOneFile((File) any())).thenReturn(data);
        mockMvc.perform(MockMvcRequestBuilders.get(extraction_file_path)
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(files)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testExtractFileIOFailure() throws Exception {
        when(extractorService.getAllFiles(anyString())).thenReturn(files);

        when(extractorService.extractOneFile((File) any())).thenThrow(IOException.class);
        mockMvc.perform(MockMvcRequestBuilders.get(extraction_file_path)
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(files)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testExtractFileTikaFailure() throws Exception {
        when(extractorService.getAllFiles(anyString())).thenReturn(files);

        when(extractorService.extractOneFile((File) any())).thenThrow(TikaException.class);
        mockMvc.perform(MockMvcRequestBuilders.get(extraction_file_path)
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(files)))
                .andExpect(MockMvcResultMatchers.status().isExpectationFailed())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testExtractFileSAXFailure() throws Exception {
        when(extractorService.getAllFiles(anyString())).thenReturn(files);

        when(extractorService.extractOneFile((File) any())).thenThrow(SAXException.class);
        mockMvc.perform(MockMvcRequestBuilders.get(extraction_file_path)
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(files)))
                .andExpect(MockMvcResultMatchers.status().isExpectationFailed())
                .andDo(MockMvcResultHandlers.print());
    }

    private static String asJsonString(final Object obj)
    {
        try{
            System.out.println(new ObjectMapper().writeValueAsString(obj));
            return new ObjectMapper().writeValueAsString(obj);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
