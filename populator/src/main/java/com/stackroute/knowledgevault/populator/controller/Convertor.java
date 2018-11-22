package com.stackroute.knowledgevault.populator.controller;

import com.github.jsonldjava.core.JsonLdOptions;
import com.github.jsonldjava.core.JsonLdProcessor;
import com.github.jsonldjava.utils.JsonUtils;
import com.stackroute.knowledgevault.domain.*;
import com.stackroute.knowledgevault.domain.MedicalCondition;
import com.stackroute.knowledgevault.populator.repository.StructureRepo;
import com.stackroute.knowledgevault.populator.service.*;
import org.neo4j.driver.v1.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class Convertor {
    @Autowired
    private MedicalConditionService medicalConditionService;
    @Autowired
    private AnatomyService anatomyService;
    @Autowired
    private SymptomService symptomService;
    @Autowired
    StructureRepo structureRepo;

   @Autowired
    MedicalGraphService medicalGraphService;

    public Convertor(MedicalGraphService medicalGraphService) {
        this.medicalGraphService = medicalGraphService;
    }

    static Long symptomId=0L;
    static Long anatomyId=1L;
    static Long treatmentId=0L;
    static Long causeId=0L;
    static Long id=1L;
    private Driver driver;
    @Autowired
    ReadJsonld readJsonld;

    @GetMapping("/createmt/{input}")
    public void createMT(@PathVariable(value="input") String input) {
        structureRepo.createMT(input);
    }
    @GetMapping("/createmtr/{input}")
    public void createMTR(@PathVariable(value="input") String input) {
        structureRepo.createMTR(input);
    }
    @GetMapping("/creater/{node1}/{rel}/{node2}")
    public void createStructure(@PathVariable(value="node1") String node1,@PathVariable(value="rel") String rel,@PathVariable(value="node2") String node2){
        structureRepo.createR(node1,rel,node2);
    }
    @GetMapping("/ra/{input}")
    public void input(@PathVariable(value="input") String input) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        InputStream inputStream = new FileInputStream("populator/src/main/Docs/"+input+".json");
        Object jsonObject = JsonUtils.fromInputStream(inputStream);
        Map context = new HashMap();
        Map<String, Object> root = (Map) jsonObject;
        MedicalCondition medicalCondition=readJsonld.getMedicalCondition(root);
        AnatomicalStructure anatomicalStructure =readJsonld.getAnatomicalStructure(root);
        List<MedicalSymptom> medicalSymptomList =readJsonld.getSymptoms(root);
        medicalGraphService.populate(1,medicalCondition, anatomicalStructure, medicalSymptomList);
        //medicalGraphService.makegraph(1,medicalCondition,anatomicalStructure,medicalSymptomList);

    }
    @GetMapping("/{input}")
    public void parse(@PathVariable(value="input") String input) throws IOException {
        ResponseEntity responseEntity;
        InputStream inputStream = new FileInputStream("populator/src/main/Docs/"+input+".json");
        Object jsonObject = JsonUtils.fromInputStream(inputStream);
        Map context = new HashMap();
        JsonLdOptions options = new JsonLdOptions();
        Object compact = JsonLdProcessor.compact(jsonObject, context, options);
        String symptomName="",symptomType="";
        boolean anatomyPresent=false,causePresent=false,conditionPresent=false,symptomPresent=false,treatmentPresent=false;
        AnatomicalStructure anatomicalStructure =null;
        Map<String, Object> root = (Map) jsonObject;
        String type=(String) root.get("@type");
        String name=(String)root.get("alternateName");
        Map<String,String> anatomyMap= (Map<String, String>) root.get("associatedAnatomy");
        String anatomyName=anatomyMap.get("name");
        String anatomyType=anatomyMap.get("@type");
        List<AnatomicalStructure> anatomicalStructureList =anatomyService.anatomyList();

            anatomicalStructure =new AnatomicalStructure(anatomyName,anatomyType);
            anatomyService.saveAnatomy(anatomicalStructure);

        Map<String,String> codeMap= (Map<String, String>) root.get("code");
        Map<String,List> differentialDiagnosis= (Map<String, List>) root.get("differentialDiagnosis");
        List<Map<String,String>> distinguishingSignList=differentialDiagnosis.get("distinguishingSign");
        Iterator signIterator = distinguishingSignList.iterator();
        List<MedicalSymptom> medicalSymptomList =new ArrayList<>();
        while(signIterator.hasNext()){
            symptomPresent=false;
            Map<String,String> sym= (Map<String, String>) signIterator.next();
            symptomName=sym.get("name");
            symptomType=sym.get("@type");
            List<MedicalSymptom> medicalSymptomList1 =symptomService.symptomList();


                symptomId++;
                MedicalSymptom medicalSymptom = new MedicalSymptom(  symptomName,symptomType);
                symptomService.saveSymptom(medicalSymptom);
                medicalSymptomList.add(medicalSymptom);

        }

        medicalConditionService.saveCondition(new MedicalCondition(name,type, anatomicalStructure, medicalSymptomList));
        System.out.println(medicalConditionService.conditionList());
    }

}