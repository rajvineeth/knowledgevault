package com.stackroute.controller;

import com.stackroute.dao.DocumentDao;
import com.stackroute.domain.ExtractedFileData;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/v1/doc")
public class DocumentController {

    private DocumentDao documentDao;

    public DocumentController(DocumentDao documentDao) {
        this.documentDao = documentDao;
    }
//    @Autowired
//    private KafkaTemplate<String, Collection<Map>> kafkaTemplate;
//    private static final String Topic="kafka_example";
    @PostMapping
    public ExtractedFileData insertDoc(@RequestBody ExtractedFileData extractedFileData){
        return documentDao.insertDoc(extractedFileData);
    }
    @GetMapping("/showall")
    public Collection<Map<String, Object>> showAll(){
        return documentDao.getAll();
    }
    @GetMapping("/search")
    public Collection<Map> searchAll() throws IOException {
        Collection<Map> res=documentDao.getKeywords();
       // kafkaTemplate.send(Topic, res);
        return res;
    }
    @GetMapping("/{content}")
    public Collection<Map<String, Object>> searchContent(@PathVariable String content){return documentDao.search(content);}

}
