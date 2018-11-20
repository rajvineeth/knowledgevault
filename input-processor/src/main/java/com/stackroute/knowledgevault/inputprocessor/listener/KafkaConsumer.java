package com.stackroute.knowledgevault.inputprocessor.listener;

import com.stackroute.knowledgevault.domain.Input;
import com.stackroute.knowledgevault.domain.JSONld;
import com.stackroute.knowledgevault.inputprocessor.utilities.FillUpData;
import com.stackroute.knowledgevault.inputprocessor.utilities.Processor;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    private KafkaProducer producer;

    @Autowired
    private Processor processor;


    @KafkaListener(topics="kafka_example_jsonh",groupId = "group_json", containerFactory= "documentKafkaListenerFactory")
    public void consumejson(Input INPUT){
        LOGGER.info("consumed message: {}",INPUT.toString());
        processor = new Processor();
        JSONObject obj = FillUpData.fill(processor.paraProcessing(INPUT.getText()));
        LOGGER.info("******####******\n\nJSONObject:-\n{}\n\n*****####*****", obj);
        producer.post(new JSONld(obj));

        processor = null;

        LOGGER.info("producer message: hey !! i sent the message");


    }

}
