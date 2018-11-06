package com.stackroute.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public List<String> getClass(String FileName, int index){
        String line = "";
        String csvSplitBy = ",";
        List<String> resultList = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(FileName))){
            int counter = 0;
            while((line = br.readLine()) != null && counter <= 3300){
                String[] info = line.split(csvSplitBy);
                //System.out.println(info[1]);
                resultList.add(info[index]);
                counter++;
            }


        }catch (IOException e){
            e.printStackTrace();
        }
        return resultList;
    }

}
