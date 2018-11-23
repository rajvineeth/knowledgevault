package com.stackroute.knowledgevault.inputtagger.communicator;

import com.stackroute.knowledgevault.domain.InputLemma;
import com.stackroute.knowledgevault.domain.InputPOS;
import com.stackroute.knowledgevault.domain.InputToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaConsumer {

    //to log data on the console
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    /*
     * This method consumes data from kafka server and makes call to kafka producer.
     */
    @KafkaListener(topics = "input-POS", groupId = "group_json", containerFactory = "posKafkaListenerFactory")
    public void consumejson(InputPOS inputPOS){
        LOGGER.info("LemmaUserInput: {}",inputPOS.toString());
    }

    @KafkaListener(topics = "input-lemma", groupId = "group_json", containerFactory = "lemmaKafkaListenerFactory")
    public void consumejson(InputLemma inputLemma){
        LOGGER.info("LemmaUserInput: {}",inputLemma.toString());
    }

    @KafkaListener(topics = "input-token", groupId = "group_json", containerFactory = "tokenKafkaListenerFactory")
    public void consumejson(InputToken inputToken){
        LOGGER.info("LemmaUserInput: {}",inputToken.toString());
    }


}
