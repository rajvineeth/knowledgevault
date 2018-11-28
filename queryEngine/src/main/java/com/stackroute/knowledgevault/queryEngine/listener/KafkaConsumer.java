package com.stackroute.knowledgevault.queryEngine.listener;

import com.stackroute.knowledgevault.domain.FrontEndData;
import com.stackroute.knowledgevault.domain.OutputResult;
import com.stackroute.knowledgevault.domain.ProcessedInput;
import com.stackroute.knowledgevault.queryEngine.service.DriverInit;
import com.stackroute.knowledgevault.queryEngine.service.QueryService;
import org.neo4j.driver.v1.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Service
public class KafkaConsumer {

//    @Autowired
//    private KafkaProducer producer;
    private DriverInit driver = new DriverInit("bolt://localhost:7687", "neo4j", "123456");
    private QueryService queryService = new QueryService();
    private static final Logger LOGGER = LoggerFactory.getLogger(com.stackroute.knowledgevault.queryEngine.listener.KafkaConsumer.class);
    @KafkaListener(topics = "queryInput", groupId = "group_json_query", containerFactory = "userKafkaListenerFactory")
    @GetMapping("/results")
    public void consumejson(ProcessedInput processedInput) {
        LOGGER.info("consumed message");
        Driver drive = driver.getDriver();
        for (Map.Entry<String, String> entry : processedInput.getKeyValue().entrySet()) {
            FrontEndData.list.clear();
            LOGGER.info(entry.getKey());
            LOGGER.info(entry.getValue());
            FrontEndData.list.add(queryService.runquery(drive, entry.getKey(), entry.getValue()));
//            producer.post(pi);

        }
        LOGGER.info("hey i sent the complete data");
//      queryService.close(drive);
    }
}

