package com.stackroute.knowledgevault.paragraphprocessor.communicators;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.knowledgevault.domain.Document;
import com.stackroute.knowledgevault.domain.JSONld;
import com.stackroute.knowledgevault.paragraphprocessor.Processor;
import com.stackroute.knowledgevault.paragraphprocessor.utilities.FillUpData;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class KafkaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    private KafkaProducer producer;

    private Processor pst;
    /**
     * This method consumes the data for which it is listening kafka cluster to
     * @param: the wanted message
     */
    @KafkaListener(topics="cgi4",groupId = "group_json", containerFactory= "userKafkaListenerFactory")
    public void consumejson(Document data){
        LOGGER.info("consumed message: {}",data.toString());

        pst = new Processor();
        JSONObject obj = FillUpData.fill(pst.paraProcessing("my name is neeraj and i have lungs cancer."));
        ObjectMapper mapper = new ObjectMapper();
        try {
            JSONld usr = mapper.readValue(obj.toString(), JSONld.class);
            producer.post(usr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONld jsoNld = new JSONld(obj);
//        LOGGER.info("jsonld object: {}",jsoNld.getData());
        pst = null;
        LOGGER.info("producer message: hey !! i sent the message");
    }
}
