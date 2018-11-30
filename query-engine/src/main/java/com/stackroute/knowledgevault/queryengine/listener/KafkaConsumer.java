package com.stackroute.knowledgevault.queryengine.listener;

import com.stackroute.knowledgevault.domain.OutputResult;
import com.stackroute.knowledgevault.domain.ProcessedInput;
import com.stackroute.knowledgevault.queryengine.service.DriverInit;
import com.stackroute.knowledgevault.queryengine.service.QueryService;
import org.neo4j.driver.v1.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class KafkaConsumer {

    public static List<OutputResult> list = new ArrayList<>();

//    @Autowired
//    private KafkaProducer producer;
    private DriverInit driver = new DriverInit("bolt://172.23.239.143:7687", "neo4j", "123456");
    private QueryService queryService = new QueryService();
    private static final Logger LOGGER = LoggerFactory.getLogger(com.stackroute.knowledgevault.queryengine.listener.KafkaConsumer.class);
    @KafkaListener(topics = "queryInput", groupId = "group_json_query", containerFactory = "userKafkaListenerFactory")
    public void consumejson(ProcessedInput processedInput) {
        LOGGER.info("consumed message");
        Driver drive = driver.getDriver();
        list.clear();
        for (Map.Entry<String, String> entry : processedInput.getKeyValue().entrySet()) {
            LOGGER.info(entry.getKey());
            LOGGER.info(entry.getValue());
            list = queryService.runquery(drive, entry.getKey(), entry.getValue());
//            OutputResult res = new OutputResult("cancer","disease","pain","symptom", "indicated by");

//            producer.post(pi);

        }
        LOGGER.info("hey i sent the complete data");
//      queryService.close(drive);
    }
}

