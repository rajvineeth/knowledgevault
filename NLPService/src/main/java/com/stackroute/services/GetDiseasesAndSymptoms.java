package com.stackroute.services;

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
}

