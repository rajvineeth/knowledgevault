package com.stackroute.knowledgevault.documentidentifier.services;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class GetDiseasesAndSymptoms {

    private Logger logger;

    public List<String> getDiseases(){
        CSVReader csvReader = new CSVReader();
        return csvReader.getClass("/knowledge-vault/document-identifier/DOID.csv", 1);

    }

    public List<String> getSymptoms(){
        CSVReader csvReader = new CSVReader();
        return csvReader.getClass("/knowledge-vault/document-identifier/SYMP.csv", 1);
    }

    public List<String> getBodyParts(){
        String listingFolder = "/knowledge-vault/document-identifier/";
        File file = new File(listingFolder + "bodypartlist.txt");
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

