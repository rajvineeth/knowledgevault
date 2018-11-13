package com.stackroute.knowledgevault.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CSVReader {

    Logger logger;

    public List<String> getClass(String fileName, int index){
        String line = "";
        String csvSplitBy = ",";
        List<String> resultList = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
            int counter = 0;
            while((line = br.readLine()) != null && counter <= 3300){
                String[] info = line.split(csvSplitBy);
                resultList.add(info[index]);
                counter++;
            }


        }catch (IOException e){
            logger.info(e.getMessage());
        }
        return resultList;
    }

}
