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

    public static Set<OutputResult> set = new HashSet<>();
    public static Set<OutputResult> set2 = new HashSet<>();

    private DriverInit driver = new DriverInit("bolt://127.0.0.1:7687", "neo4j", "123456");
    @Autowired
    private QueryService queryService;
    private static final Logger LOGGER = LoggerFactory.getLogger(com.stackroute.knowledgevault.queryengine.listener.KafkaConsumer.class);
    @KafkaListener(topics = "queryInput", groupId = "group_json_query", containerFactory = "userKafkaListenerFactory")
    public void consumejson(ProcessedInput processedInput) {
        LOGGER.info("consumed message {}", processedInput);
        Driver drive = driver.getDriver();

        set.clear();
        for (Map.Entry<String, String> entry : processedInput.getKeyValue().entrySet()) {
            LOGGER.info("entry : {}", entry);
            LOGGER.info(entry.getKey());
            LOGGER.info(entry.getValue());
            if(entry.getValue().compareTo("MedicalCondition")!=0) {
                LOGGER.info("inside if");
                set2 = queryService.runquery(drive, entry.getKey(), entry.getValue());
                set.addAll(set2);
            }
            else{
                LOGGER.info("inside else");
                set2 = queryService.runquery2(drive, entry.getKey(), entry.getValue());
                set.addAll(set2);
            }

        }
        LOGGER.info("hey i sent the complete data");
    }
}

