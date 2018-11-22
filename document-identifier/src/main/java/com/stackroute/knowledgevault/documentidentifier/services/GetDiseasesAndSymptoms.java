package com.stackroute.knowledgevault.documentidentifier.services;

import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class GetDiseasesAndSymptoms {

    private String diseaseDictionary="/knowledge-vault/document-identifier/DOID.csv";

    private String symptomDictionary="/knowledge-vault/document-identifier/SYMP.csv";

    private String bodyPartDictionary="/knowledge-vault/document-identifier/bodypartlist.txt";

    private Logger logger;

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
        try (BufferedReader br = new BufferedReader(new FileReader(file));){
            String st;
            while((st = br.readLine()) != null){
                if(!st.isEmpty())
                    bodyparts.add(st);
            }
            return bodyparts;
        }catch (Exception e){
            logger.info(e.getMessage());
        }
        return bodyparts;
    }
}