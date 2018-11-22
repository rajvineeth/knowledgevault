package com.stackroute.knowledgevault.documentidentifier.services;

import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class GetDiseasesAndSymptoms {

    @Value("${dictionary.disease}")
    private String diseaseDictionary;

    @Value("${dictionary.symptom}")
    private String symptomDictionary;

    @Value("${dictionary.body_parts}")
    private String bodyPartDictionary;

    public List<String> getDiseases(){
        CSVReader csvReader = new CSVReader();
        return csvReader.getClass(diseaseDictionary, 1);

    }

    public List<String> getSymptoms(){
        CSVReader csvReader = new CSVReader();
        return csvReader.getClass(symptomDictionary, 1);
    }

    public List<String> getBodyParts(){
        File file = new File(bodyPartDictionary);
        List<String> bodyparts = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while((st = br.readLine()) != null){
                if(st.isEmpty() == false)
                    bodyparts.add(st);
            }
            return bodyparts;
        }catch (Exception e){
            e.printStackTrace();
        }
        return bodyparts;
    }
}