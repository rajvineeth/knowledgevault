package com.stackroute.controller;

import com.stackroute.dao.DocumentDao;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/books")
public class DocumentController {

    private DocumentDao documentDao;

    public DocumentController(DocumentDao documentDao) {
        this.documentDao = documentDao;
    }
    @PostMapping
    public com.stackroute.domain.Document insertDoc(@RequestBody com.stackroute.domain.Document document){
        return documentDao.insertDoc(document);
    }
    @GetMapping("/showall")
    public Collection<Map<String, Object>> showAll(){
        return documentDao.getAll();
    }
    @GetMapping("/search")
    public Collection<Map> searchAll() throws IOException {
        return documentDao.getKeywords();
    }
    @GetMapping("/{content}")
    public Collection<Map<String, Object>> searchContent(@PathVariable String content){return documentDao.search(content);}

}
