package com.stackroute.knowledgevault.queryEngine.listener;

import com.stackroute.knowledgevault.domain.ProcessedInput;
import com.stackroute.knowledgevault.queryEngine.service.DriverInit;
import com.stackroute.knowledgevault.queryEngine.service.QueryService;
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

<<<<<<< HEAD
    @Autowired
    private KafkaProducer producer;

    private DriverInit driver = new DriverInit("bolt://localhost:7687", "neo4j", "123456");
=======
    private DriverInit driver = new DriverInit("bolt://172.23.239.179:7687", "neo4j", "123456");
>>>>>>> 95da466924c1afe1b7e35e49ffd24a2b8819830d
    private QueryService queryService = new QueryService();
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "queryInput", groupId = "group_json_query", containerFactory = "userKafkaListenerFactory")
    public void consumejson(ProcessedInput processedInput) {
        LOGGER.info("consumed message");
        Driver drive = driver.getDriver();
        for(Map.Entry<String,String> entry: processedInput.getKeyValue().entrySet()) {
            LOGGER.info(entry.getKey());
            LOGGER.info(entry.getValue());
            ProcessedInput pi = queryService.runquery(drive,entry.getKey(),entry.getValue());

            producer.post(pi);
        }
        LOGGER.info("hey i sent the complete data");
//        queryService.close(drive);

    }
}

