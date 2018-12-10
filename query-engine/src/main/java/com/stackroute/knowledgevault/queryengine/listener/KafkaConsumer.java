package com.stackroute.knowledgevault.queryengine.listener;

import com.stackroute.knowledgevault.domain.OutputResult;
import com.stackroute.knowledgevault.domain.ProcessedInput;
import com.stackroute.knowledgevault.queryengine.service.DriverInit;
import com.stackroute.knowledgevault.queryengine.service.QueryService;
import org.neo4j.driver.v1.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class KafkaConsumer {

    public  static Set<OutputResult> set = new HashSet<>();
    public  Set<OutputResult> set2;
    private List<Set<OutputResult>> setList=new ArrayList<>();
    public static LinkedHashSet<OutputResult> res=new LinkedHashSet<>();
    private DriverInit driver = new DriverInit("bolt://127.0.0.1:7687", "neo4j", "123456");

    @Autowired
    private QueryService queryService;
    private static final Logger LOGGER = LoggerFactory.getLogger(com.stackroute.knowledgevault.queryengine.listener.KafkaConsumer.class);
    @KafkaListener(topics = "queryInput", groupId = "group_json_query", containerFactory = "userKafkaListenerFactory")
    public void consumejson(ProcessedInput processedInput) {
        LOGGER.info("consumed message {}", processedInput);
        Driver drive = driver.getDriver();

        set.clear();
        setList.clear();
        res.clear();
        for (Map.Entry<String, String> entry : processedInput.getKeyValue().entrySet()) {
            set2=new HashSet<>();
            LOGGER.info("entry : {}", entry);
            LOGGER.info(entry.getKey());
            LOGGER.info(entry.getValue());
            if(entry.getValue().compareTo("MedicalCondition")!=0) {
                LOGGER.info("inside if");
                set2=new HashSet<>();
                set2 = queryService.runquery(drive, entry.getKey(), entry.getValue());
                setList.add(set2);
               set.addAll(set2);
            }
            else{
                LOGGER.info("inside else");
                set2 = queryService.runquery2(drive, entry.getKey(), entry.getValue());
                setList.add(set2);
                set.addAll(set2);
            }
        }
        intersect(setList);
        for(int i=setList.size()-1;i>0;i--){
            List<List<Set<OutputResult>>> subset=combinate(setList,i);
            for(List l:subset){
                intersect(l);
            }
        }
        LOGGER.info("hey i sent the complete data");
    }
    public LinkedHashSet<OutputResult> intersect(List<Set<OutputResult>> setList){
        LinkedHashSet<OutputResult> resCross = new LinkedHashSet<>();
        resCross.addAll(setList.get(0));
        for (int i = 1; i < setList.size(); i++) {
            resCross.retainAll(setList.get(i));
        }
        for (OutputResult outputResult : resCross) {
            res.add(outputResult);
        }
        return res;
    }
    public static LinkedHashSet<OutputResult> intersection(Set<OutputResult> s1, Set<OutputResult> s2) {
        LinkedHashSet<OutputResult> res = new LinkedHashSet<>();
        res.addAll(s1);
        res.retainAll(s2);
        return res;
    }
    public List<List<Set<OutputResult>>> combinate(List<Set<OutputResult>> input,int size){
        int k = size;
        List<List<Set<OutputResult>>> subsets = new ArrayList<>();
        int[] s = new int[k];
        if (k <= input.size()) {
            for (int i = 0; (s[i] = i) < k - 1; i++);
            subsets.add(getSubset(input, s));
            for(;;) {
                int i;
                for (i = k - 1; i >= 0 && s[i] == input.size() - k + i; i--);
                if (i < 0) {
                    break;
                }
                s[i]++;
                for (++i; i < k; i++) {
                    s[i] = s[i - 1] + 1;
                }
                subsets.add(getSubset(input, s));
            }
        }
        return subsets;
    }
    public List<Set<OutputResult>> getSubset(List<Set<OutputResult>> input, int[] subset) {
        List<Set<OutputResult>> result = new ArrayList<>();
        for (int i = 0; i < subset.length; i++)
            result.add(input.get(subset[i]));
        return result;
    }
}

