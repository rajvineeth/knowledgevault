package com.stackroute.knowledgevault.queryengine.controller;


import com.stackroute.knowledgevault.domain.ExtractedFileData;
import com.stackroute.knowledgevault.domain.OutputResult;
import com.stackroute.knowledgevault.domain.ParaContent;
import com.stackroute.knowledgevault.queryengine.listener.KafkaConsumer;
import com.stackroute.knowledgevault.queryengine.service.DriverInit;
import com.stackroute.knowledgevault.queryengine.service.ExtractedDataService;
import com.stackroute.knowledgevault.queryengine.service.ParaContentService;
import com.stackroute.knowledgevault.queryengine.service.QueryService;
import org.neo4j.driver.v1.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin("*")
@RestController
@RequestMapping(value="api/v1")
public class QueryController {


    private DriverInit driver = new DriverInit("bolt://127.0.0.1:7687", "neo4j", "123456");

    Driver drive = driver.getDriver();

    ParaContentService paraContentService;
    ExtractedDataService extractedDataService;
    @Autowired
    public QueryController(ParaContentService paraContentService,ExtractedDataService extractedDataService) {
        this.paraContentService = paraContentService;
        this.extractedDataService=extractedDataService;
    }
    @Autowired
    private QueryService queryService;

    @GetMapping("/results")
    public ResponseEntity<?> getResults(){
//        Set<OutputResult> list = FrontEndData.list;
        System.out.println(KafkaConsumer.res);
        ResponseEntity<?> responseEntity = new ResponseEntity<Set<OutputResult>>((Set<OutputResult>) KafkaConsumer.res, HttpStatus.OK);
        return responseEntity;
    }
    @GetMapping("/para/{id}")
    public ResponseEntity<?> getResults2(@PathVariable(value="id") int paraId){
     ResponseEntity responseEntity;
            Optional<ParaContent> savedList = paraContentService.getParaById(paraId);
            Integer id=savedList.get().getDocId();
            Optional<ExtractedFileData> doc=extractedDataService.getDocById(id);
            String content=doc.get().getContent();
            responseEntity = new ResponseEntity<Optional<ParaContent>>(savedList, HttpStatus.OK);

        return responseEntity;
    }
    @GetMapping("/results1/{a}/{b}")
    public ResponseEntity<?> getResults5(@PathVariable(value ="a")String a,@PathVariable (value="b")String b){
//        Set<OutputResult> list = FrontEndData.list;
//        System.out.println(KafkaConsumer.list);
        ResponseEntity<?> responseEntity = new ResponseEntity<Set<OutputResult>>( queryService.runquery(drive,a,b), HttpStatus.OK);
        return responseEntity;
    }
}
