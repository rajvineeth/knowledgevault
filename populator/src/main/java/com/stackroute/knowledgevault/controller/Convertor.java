package com.stackroute.knowledgevault.controller;

import com.github.jsonldjava.core.JsonLdOptions;
import com.github.jsonldjava.core.JsonLdProcessor;
import com.github.jsonldjava.utils.JsonUtils;
import com.stackroute.knowledgevault.domain.Cause;
import com.stackroute.knowledgevault.domain.MedicalCondition;
import com.stackroute.knowledgevault.service.CauseService;
import com.stackroute.knowledgevault.service.MedicalConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class Convertor {
    @Autowired
    private MedicalConditionService medicalConditionService;
    @Autowired
    private CauseService causeService;
//    @Autowired
//    public Convertor(MedicalConditionService medicalConditionService, CauseService causeService) {
//        this.medicalConditionService = medicalConditionService;
//        this.causeService = causeService;
//    }

    @GetMapping("")
    public void parse() throws IOException {
        ResponseEntity responseEntity;
        InputStream inputStream = new FileInputStream("populator/src/main/Docs/input.json");
        Object jsonObject = JsonUtils.fromInputStream(inputStream);
        Map context = new HashMap();
        JsonLdOptions options = new JsonLdOptions();
        Object compact = JsonLdProcessor.compact(jsonObject, context, options);
        System.out.println(JsonUtils.toPrettyString(compact));

        Map<String, Object> root = (Map) jsonObject;
        String type=(String) root.get("@type");
        String name=(String)root.get("alternateName");
        Long id=1L;

        ArrayList<Map<String, String>> causeMap = (ArrayList<Map<String, String>>) root.get("cause");
        String causeName=(String)causeMap.get(0).get("name");
        String causeType=(String)causeMap.get(0).get("@type");
        Long causeId=1L;



        medicalConditionService.saveCondition(new MedicalCondition(id,type,name));
        causeService.saveCause(new Cause(causeId,causeType,causeName));
    }
}