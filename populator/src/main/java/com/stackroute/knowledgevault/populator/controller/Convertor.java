package com.stackroute.knowledgevault.populator.controller;

import com.github.jsonldjava.core.JsonLdOptions;
import com.github.jsonldjava.core.JsonLdProcessor;
import com.github.jsonldjava.utils.JsonUtils;
import com.stackroute.knowledgevault.domain.*;
import com.stackroute.knowledgevault.domain.Cause;
import com.stackroute.knowledgevault.domain.MedicalCondition;
import com.stackroute.knowledgevault.populator.service.AnatomyService;
import com.stackroute.knowledgevault.populator.service.CauseService;
import com.stackroute.knowledgevault.populator.service.MedicalConditionService;
import com.stackroute.knowledgevault.populator.service.SymptomService;
import com.stackroute.knowledgevault.populator.service.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class Convertor {
    @Autowired
    private MedicalConditionService medicalConditionService;
    @Autowired
    private CauseService causeService;
    @Autowired
    private AnatomyService anatomyService;
    @Autowired
    private SymptomService symptomService;
    @Autowired
    private TreatmentService treatmentService;
    static Long symptomId=0L;
    static Long anatomyId=1L;
    static Long treatmentId=0L;
    static Long causeId=0L;
    static Long id=1L;
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
        Anatomy anatomy=null;
        Map<String, Object> root = (Map) jsonObject;
        String type=(String) root.get("@type");
        String name=(String)root.get("alternateName");
        Map<String,String> anatomyMap= (Map<String, String>) root.get("associatedAnatomy");
        String anatomyName=anatomyMap.get("name");
        String anatomyType=anatomyMap.get("@type");
        List<Anatomy> anatomyList=anatomyService.anatomyList();
        for(Anatomy anatomy1:anatomyList){
            if(anatomy1.getAnatomyName().equals(anatomyName)){
                anatomy=anatomy1;
                anatomyPresent=true;
            }
        }
        if(!anatomyPresent){
            anatomy=new Anatomy(anatomyId++,anatomyType,anatomyName);
            anatomyService.saveAnatomy(anatomy);
        }
        Map<String,String> codeMap= (Map<String, String>) root.get("code");
        Map<String,List> differentialDiagnosis= (Map<String, List>) root.get("differentialDiagnosis");
        List<Map<String,String>> distinguishingSignList=differentialDiagnosis.get("distinguishingSign");
        Iterator signIterator = distinguishingSignList.iterator();
        List<Symptom> symptomList=new ArrayList<>();
        while(signIterator.hasNext()){
            symptomPresent=false;
            Map<String,String> sym= (Map<String, String>) signIterator.next();
            symptomName=sym.get("name");
            symptomType=sym.get("@type");
            List<Symptom> symptomList1=symptomService.symptomList();
            for(Symptom symptom:symptomList1){
                if(symptom.getSymptomName().equals(symptomName)){
                    symptomList.add(symptom);
                    symptomPresent=true;
                }
            }
            if(!symptomPresent) {
                symptomId++;
                Symptom symptom = new Symptom(symptomId, symptomType, symptomName);
                symptomService.saveSymptom(symptom);
                symptomList.add(symptom);
            }
        }

        List<Map<String,Object>> treatment= (List<Map<String, Object>>) root.get("possibleTreatment");
        Iterator treatmentIterator=treatment.iterator();
        String treatmentName="",treatmentType="";

        List<Treatment> treatmentList=new ArrayList<>();
        while(treatmentIterator.hasNext()){
            treatmentPresent=false;
            Map<String,Object> stringObjectMap= (Map<String, Object>) treatmentIterator.next();
            treatmentName= (String) stringObjectMap.get("name");
            treatmentType= (String) stringObjectMap.get("@type");
            List<Treatment> treatmentList1=treatmentService.treatmentList();
            for(Treatment treatment1:treatmentList1){
                if(treatment1.getTreatmentName().equals(treatmentName)){
                    treatmentList.add(treatment1);
                    treatmentPresent=true;
                }
            }
            if(!treatmentPresent) {
                treatmentId++;
                ArrayList<Map<String, String>> dose = (ArrayList<Map<String, String>>) stringObjectMap.get("DoseSchedule");
                String doseUnit = dose.get(0).get("doseUnit");
                String frequency = dose.get(1).get("frequency");
                Treatment treatment1 = new Treatment(treatmentId, treatmentType, treatmentName, doseUnit, frequency);
                treatmentService.saveTreatment(treatment1);
                treatmentList.add(treatment1);
            }
        }



        ArrayList<Map<String, String>> causeList = (ArrayList<Map<String, String>>) root.get("cause");
        Iterator mapIterator = causeList.iterator();
        String causeName="",causeType="";

        List<Cause> causeList1=new ArrayList<>();

        while (mapIterator.hasNext()) {
            causePresent=false;
            Map<String,String> causeMap = (Map<String, String>) mapIterator.next();
            causeName=(String)causeMap.get("name");
            List<Cause> causeList2=causeService.causeList();
            for(Cause cause:causeList2){
                if(cause.getCauseName().equals(causeName)){
                    causeList1.add(cause);
                    causePresent=true;
                }
            }
            if(!causePresent) {
                causeType = (String) causeMap.get("@type");
                causeId++;
                Cause cause = new Cause(causeId, causeType, causeName);
                causeService.saveCause(cause);
                causeList1.add(cause);
            }
        }
        medicalConditionService.saveCondition(new MedicalCondition(id++,type,name,causeList1,anatomy,symptomList,treatmentList));
        System.out.println(medicalConditionService.conditionList());
    }

}