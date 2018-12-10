package com.stackroute.knowledgevault.inputtagger.communicator;

import com.stackroute.knowledgevault.domain.InputLemma;
import com.stackroute.knowledgevault.domain.InputObject;
import com.stackroute.knowledgevault.domain.InputPOS;
import com.stackroute.knowledgevault.inputtagger.utils.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class KafkaConsumer {

    //to log data on the console
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    private InputObject inputObject = new InputObject();
    @Autowired
    private Processor processor;

    @Autowired
    private KafkaProducer producer;
    /*
     * This method consumes data from kafka server and makes call to kafka producer.
     */
    @KafkaListener(topics = "input-POS", groupId = "group_json_tag_POS", containerFactory = "posKafkaListenerFactory")
    public void consumejson(InputPOS inputPOS){
        LOGGER.info("inside POS KafkaConsumer.consumejson()");
        LOGGER.info("TaggerPOSUserInput: {}",inputPOS.toString());
        this.inputObject.setId(inputPOS.getId());
        this.inputObject.setPoses(inputPOS.getPoses());

        if (inputObject.getLemmas() != null && inputObject.getPoses() != null){
            LOGGER.info("inside pos consumer().if()...");
            InputObject passingVar = this.inputObject;
            this.inputObject = new InputObject();
            producer.post(processor.process(passingVar));
        }
    }

    @KafkaListener(topics = "input-lemma", groupId = "group_json_tag_lemma", containerFactory = "lemmaKafkaListenerFactory")
    public void consumejson(InputLemma inputLemma){
        LOGGER.info("inside lemma KafkaConsumer.consumejson()");
        LOGGER.info("TaggerLemmaUserInput: {}",inputLemma.toString());
        this.inputObject.setId(inputLemma.getId());
        this.inputObject.setLemmas(inputLemma.getLemmas());

        if (inputObject.getLemmas() != null && inputObject.getPoses() != null){
            LOGGER.info("inside lemma consumer().if()...");
            InputObject paasingVar = this.inputObject;
            this.inputObject = new InputObject();
            producer.post(processor.process(paasingVar));
        }

    }

}
