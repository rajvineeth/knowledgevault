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

    private InputObject inputObject = null;
    private boolean wait = true;
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
        this.inputObject = new InputObject(inputPOS.getId());
        this.inputObject.setPoses(inputPOS.getPoses());
        this.wait = false;

//        if(this.inputObject == null) {
//            LOGGER.info("inside POS KafkaConsumer.consumejson().if()");

//        }
//        else {
//            LOGGER.info("inside POS KafkaConsumer.consumejson().else");
//            this.inputObject.setPoses(inputPOS.getPoses());
//            InputObject paasingVar = this.inputObject;
//            this.inputObject = null;
//            producer.post(processor.process(paasingVar));
//        }

    }

    @KafkaListener(topics = "input-lemma", groupId = "group_json_tag_lemma", containerFactory = "lemmaKafkaListenerFactory")
    public void consumejson(InputLemma inputLemma){
        while (wait);
        LOGGER.info("inside lemma KafkaConsumer.consumejson()");
        LOGGER.info("TaggerLemmaUserInput: {}",inputLemma.toString());
        this.inputObject.setLemmas(inputLemma.getLemmas());
        InputObject paasingVar = this.inputObject;
        this.inputObject = null;
        producer.post(processor.process(paasingVar));
//        if(this.inputObject == null) {
//            LOGGER.info("inside lemma KafkaConsumer.consumejson().if()");
//            this.inputObject = new InputObject(inputLemma.getId());
//            this.inputObject.setLemmas(inputLemma.getLemmas());
//        }
//        else {
//            LOGGER.info("inside lemma KafkaConsumer.consumejson().else");

//        }
    }
//
//    @KafkaListener(topics = "input-token", groupId = "group_json", containerFactory = "tokenKafkaListenerFactory")
//    public void consumejson(InputToken inputToken){
//        LOGGER.info("TaggerLemmaUserInput: {}",inputToken.toString());
//    }


}
