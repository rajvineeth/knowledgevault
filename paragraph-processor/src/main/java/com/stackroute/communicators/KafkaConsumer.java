package com.stackroute.communicators;

import com.stackroute.algos.FullTextSearch;
import com.stackroute.domain.ParaDoc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);
//    private FullTextSearch fullTextSearch;

//    @Autowired
//    public KafkaConsumer(FullTextSearch fullTextSearch) {
//        this.fullTextSearch=fullTextSearch;
//    }

    String input;

    @KafkaListener(topics = "kafkaTest", groupId = "group_id")
    public void consume(String message) {
        this.input=message;
        LOGGER.info("Consumed message: {} ",message);
//        String response=metricsService.streamMetrics(message);
        LOGGER.info("response message: {} ","random response string");
    }

}
