package com.stackroute.knowledgevault.domain;

import org.springframework.stereotype.Component;

import java.util.*;
@Component
public class ReadJsonld {
    public MedicalCondition getMedicalCondition(Map jsonObject){
        Map<String, Object> root =  jsonObject;
        if((String)root.get("alternateName")!=null) {
            String name = (String) root.get("alternateName");
            String type=(String) root.get("@type");
            MedicalCondition medicalCondition=new MedicalCondition(name,type);
            return medicalCondition;
        }
        return  null;
    }
    public AnatomicalStructure getAnatomicalStructure(Map jsonObject){
        Map<String, Object> root =  jsonObject;
        Map<String,String> anatomyMap= (Map<String, String>) root.get("associatedAnatomy");
        if(anatomyMap.get("name")!=null) {
            String anatomyName = anatomyMap.get("name");
            String anatomyType = anatomyMap.get("@type");
            AnatomicalStructure anatomicalStructure = new AnatomicalStructure(anatomyName, anatomyType);
            return anatomicalStructure;
        }
        return null;
    }
    public List<MedicalSymptom> getSymptoms(Map jsonObject){
        Map<String, Object> root =  jsonObject;
        if((Map<String, List>) root.get("differentialDiagnosis")!=null) {
            Map<String, List> differentialDiagnosis = (Map<String, List>) root.get("differentialDiagnosis");
            List<Map<String, String>> distinguishingSignList = differentialDiagnosis.get("distinguishingSign");
            Iterator signIterator = distinguishingSignList.iterator();
            List<MedicalSymptom> medicalSymptomList = new ArrayList<>();
            while (signIterator.hasNext()) {
                Map<String, String> sym = (Map<String, String>) signIterator.next();
                String symptomName = sym.get("name");
                String symptomType = sym.get("@type");
                MedicalSymptom medicalSymptom = new MedicalSymptom(symptomName, symptomType);
                medicalSymptomList.add(medicalSymptom);
            }
            return medicalSymptomList;
        }
        return null;
    }

}
