package com.stackroute.knowledgevault.queryEngine.listener;

import com.stackroute.knowledgevault.domain.ProcessedInput;
import com.stackroute.knowledgevault.queryEngine.service.DriverInit;
import com.stackroute.knowledgevault.queryEngine.service.QueryService;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.neo4j.driver.v1.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class KafkaConsumer {

    private DriverInit driver = new DriverInit("bolt://localhost:7687", "neo4j", "123456");
    private QueryService queryService = new QueryService();
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "queryinput", groupId = "group_json", containerFactory = "userKafkaListenerFactory")
    public void consumejson(ProcessedInput processedInput) {
        LOGGER.info("consumed message");
        Driver drive = driver.getDriver();
        for(Map.Entry<String,String> entry: processedInput.getKeyValue().entrySet()) {
            queryService.runquery(drive,entry.getKey(),entry.getValue());
        }
        queryService.close(drive);

    }
}

