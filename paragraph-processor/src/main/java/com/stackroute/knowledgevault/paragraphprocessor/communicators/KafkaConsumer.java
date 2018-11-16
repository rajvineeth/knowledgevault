package com.stackroute.knowledgevault.paragraphprocessor.communicators;

import com.stackroute.knowledgevault.domain.Document;
import com.stackroute.knowledgevault.domain.JSONld;
import com.stackroute.knowledgevault.paragraphprocessor.Processor;
import com.stackroute.knowledgevault.paragraphprocessor.algos.FullTextSearch;
import com.stackroute.knowledgevault.paragraphprocessor.algos.FullTextSearchImpl;
import com.stackroute.knowledgevault.paragraphprocessor.utilities.FillUpData;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class KafkaConsumer{

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    private KafkaProducer producer;

    private Document consumendData;
    private Processor processor;

    public Document getConsumendData() {
        return consumendData;
    }

    /**
     * This method consumes the data for which it is listening kafka cluster to
     * @param: the wanted message
     */
    @KafkaListener(topics="cgi1",groupId = "group_json", containerFactory= "userKafkaListenerFactory")
    public void consumejson(Document data){
//        this.producer = new KafkaProducer();
//        this.processor = new Processor();
//        this.processor.initProcessor();
//        LOGGER.info("initialisation done....");
        LOGGER.info("consumed message: {}",data.toString());
//        this.consumendData = data;
//        Map<String,String> tags = this.processor.paraProcessing(this.consumendData.getText().toLowerCase());
//        JSONObject obj = FillUpData.fill(tags);
//        LOGGER.info("got the data....now posting it to my consumer");
//        this.producer.post(new JSONld(obj));
//        LOGGER.info("hey..i sent the data");
    }
}
