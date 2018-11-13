package com.stackroute.communicators;

import com.stackroute.domain.ExtractedFileData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    // this will store the consumed data
    String input;

    /**
     * This method consumes the data for which it is listening kafka cluster to
     * @param: the wanted message
     */
    @KafkaListener(topics="document",groupId = "group_json", containerFactory= "userKafkaListenerFactory")
    public void consumejson(ExtractedFileData data){
        LOGGER.info("consume message: {}",data.toString());
    }
}
