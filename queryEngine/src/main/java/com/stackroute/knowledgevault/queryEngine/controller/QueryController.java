package com.stackroute.knowledgevault.queryEngine.controller;


import com.stackroute.knowledgevault.domain.FrontEndData;
import com.stackroute.knowledgevault.domain.OutputResult;
import com.stackroute.knowledgevault.domain.ProcessedInput;
import com.stackroute.knowledgevault.queryEngine.service.DriverInit;
import com.stackroute.knowledgevault.queryEngine.service.QueryService;
import org.neo4j.driver.v1.Driver;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value="api/v1")
public class QueryController {

//    private DriverInit driver = new DriverInit("bolt://localhost:7687", "neo4j", "123456");
//    private QueryService queryService = new QueryService();
//    private static final Logger LOGGER=LoggerFactory.getLogger(com.stackroute.knowledgevault.queryEngine.controller.QueryController.class);
//
    @GetMapping("/results")
    public ResponseEntity<?> getResults(){
        List<OutputResult> list = FrontEndData.list;
        ResponseEntity<?> responseEntity = new ResponseEntity<>(FrontEndData.list,HttpStatus.OK);
        return responseEntity;
    }
}
