package com.stackroute.knowledgevault.queryengine.controller;


import com.stackroute.knowledgevault.queryengine.listener.KafkaConsumer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
//        Set<OutputResult> list = FrontEndData.list;
//        System.out.println(list);
        ResponseEntity<?> responseEntity = new ResponseEntity<>(KafkaConsumer.list, HttpStatus.OK);
        return responseEntity;
    }
}
