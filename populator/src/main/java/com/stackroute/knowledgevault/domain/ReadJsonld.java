package com.stackroute.knowledgevault.domain;

import org.springframework.stereotype.Component;

import java.util.*;
@Component
public class ReadJsonld {
    public MedicalCondition getMedicalCondition(Map jsonObject){
        Map<String, Object> root =  jsonObject;
        String type=(String) root.get("@type");
        String name=(String)root.get("alternateName");
        MedicalCondition medicalCondition=new MedicalCondition(name,type);
        return  medicalCondition;
    }
    public Anatomy getAnatomy(Map jsonObject){
        Map<String, Object> root =  jsonObject;
        Map<String,String> anatomyMap= (Map<String, String>) root.get("associatedAnatomy");
        String anatomyName=anatomyMap.get("name");
        String anatomyType=anatomyMap.get("@type");
        Anatomy anatomy=new Anatomy(anatomyName,anatomyType);
        return  anatomy;
    }
    public List<MedicalSymptom> getSymptoms(Map jsonObject){
        Map<String, Object> root =  jsonObject;
        Map<String,List> differentialDiagnosis= (Map<String, List>) root.get("differentialDiagnosis");
        List<Map<String,String>> distinguishingSignList=differentialDiagnosis.get("distinguishingSign");
        Iterator signIterator = distinguishingSignList.iterator();
        List<MedicalSymptom> medicalSymptomList =new ArrayList<>();
        while(signIterator.hasNext()){
            Map<String,String> sym= (Map<String, String>) signIterator.next();
            String symptomName=sym.get("name");
            String symptomType=sym.get("@type");
            MedicalSymptom medicalSymptom = new MedicalSymptom(symptomName,symptomType);
            medicalSymptomList.add(medicalSymptom);
        }
        return medicalSymptomList;
    }

}
