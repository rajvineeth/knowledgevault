package com.stackroute.knowledgevault.paragraphprocessor.communicators;

import com.stackroute.knowledgevault.domain.Document;
import com.stackroute.knowledgevault.domain.JSONld;
import com.stackroute.knowledgevault.domain.ParaContent;
import com.stackroute.knowledgevault.paragraphprocessor.service.ParaContentService;
import com.stackroute.knowledgevault.paragraphprocessor.utilities.DocProcessor;
import com.stackroute.knowledgevault.paragraphprocessor.utilities.FillUpData;
import com.stackroute.knowledgevault.paragraphprocessor.utilities.Pair;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Service class for Consuming data from Kafka-broker.
 */
@Service
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);
    private static int paraId = 1;

    @Autowired
    private KafkaProducer producer;
    ParaContent paraContent;
    @Autowired
    ParaContentService paraContentService;
    /**
     * This method consumes the data for which it is listening kafka cluster to
     * @param: the wanted message
     */
    @KafkaListener(topics="para-tokens",groupId = "group_json", containerFactory= "userKafkaListenerFactory")
    public void consumejson(Document data){

        LOGGER.info("consumed message: {}",data);

        Map<String,Double> keys = DocProcessor.performNGramAnalysis(data.getText());
        LOGGER.info("returned keys: {}",keys.keySet());

        Map<String,List<Pair>> tags = DocProcessor.generateTags(keys);
        JSONObject obj = FillUpData.fillOntology(tags);

        JSONld jsoNld = DocProcessor.json2jsonld(obj,data.getId(),paraId);
        paraContent=new ParaContent(paraId,data.getId(),data.getText());
        paraContentService.saveContent(paraContent);
	    paraId++;
        this.producer.post(jsoNld);

        LOGGER.info("JSONld object: {}",jsoNld);
        LOGGER.info("producer message: hey !! i sent the message");
    }
}
