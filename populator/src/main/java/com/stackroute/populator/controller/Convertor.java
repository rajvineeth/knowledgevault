package com.stackroute.populator.controller;

import com.github.jsonldjava.core.JsonLdOptions;
import com.github.jsonldjava.core.JsonLdProcessor;
import com.github.jsonldjava.utils.JsonUtils;
import com.stackroute.populator.domain.Cause;
import com.stackroute.populator.domain.MedicalCondition;
import com.stackroute.populator.repository.MedicalConditionRepo;
import com.stackroute.populator.service.CauseService;
import com.stackroute.populator.service.MedicalConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

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

        ArrayList<Map<String, String>> causeList = (ArrayList<Map<String, String>>) root.get("cause");
        Iterator mapIterator = causeList.iterator();
        String causeName="",causeType="";
        Long causeId=0L;
        List<Cause> causeList1=new ArrayList<>();
        //MedicalCondition medicalCondition=new MedicalCondition(id,type,name);
        while (mapIterator.hasNext()) {
            Map<String,String> causeMap = (Map<String, String>) mapIterator.next();
            causeName=(String)causeMap.get("name");
            causeType = (String) causeMap.get("@type");
            causeId++;
            Cause cause=new Cause(causeId,causeType,causeName);
            causeService.saveCause(cause);
            causeList1.add(cause);
        }
        medicalConditionService.saveCondition(new MedicalCondition(id,type,name,causeList1));
    }
}