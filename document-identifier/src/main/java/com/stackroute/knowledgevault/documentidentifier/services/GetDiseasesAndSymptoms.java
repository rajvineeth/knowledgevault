package com.stackroute.knowledgevault.documentidentifier.services;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class GetDiseasesAndSymptoms {

    public List<String> getDiseases(){
        CSVReader csvReader = new CSVReader();
        return csvReader.getClass("DOID.csv", 1);

    }

    public List<String> getSymptoms(){
        CSVReader csvReader = new CSVReader();
        return csvReader.getClass("SYMP.csv", 1);
    }

    public List<String> getBodyParts(){
        File file = new File("bodypartlist.txt");
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

