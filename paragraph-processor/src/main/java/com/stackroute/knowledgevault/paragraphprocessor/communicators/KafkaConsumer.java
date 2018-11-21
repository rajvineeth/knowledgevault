package com.stackroute.knowledgevault.paragraphprocessor.communicators;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.knowledgevault.domain.Document;
import com.stackroute.knowledgevault.domain.JSONld;
import com.stackroute.knowledgevault.paragraphprocessor.Processor;
import com.stackroute.knowledgevault.paragraphprocessor.utilities.FillUpData;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class KafkaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    private KafkaProducer producer;

    /**
     * This method consumes the data for which it is listening kafka cluster to
     * @param: the wanted message
     */
    @KafkaListener(topics="para-tokens",groupId = "group_json", containerFactory= "userKafkaListenerFactory")
    public void consumejson(Document data){
        LOGGER.info("consumed message: {}",data.toString());
        Map<String,String> tags = new HashMap<>();
        String para = data.getText();
        String[] keys = para.trim().split("\\.|\\s+");
        for(String key:keys) {
            File dictionary = new File("../../knowledge-vault/paragraph-processor/assets/taggerResource/");
            for(File f: dictionary.listFiles()) {
                try {
                    String txt = FileUtils.readFileToString(f,"UTF-8");
                    String[] words = txt.split("\\.|\\s+");
                    for(String word: words) {
                        if(word.compareTo(key)==0) tags.put(key,f.getName());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        LOGGER.info(tags.toString());
        JSONObject obj = FillUpData.fill(tags);
        ObjectMapper mapper = new ObjectMapper();
        JSONld jsoNld = null;
        try {
            HashMap<String,Object> map_data = mapper.readValue(obj.toString(), HashMap.class);
            jsoNld = new JSONld(data.getId(),map_data);
            producer.post(jsoNld);
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOGGER.info("jsonld object: {}",jsoNld.getData());
        LOGGER.info("producer message: hey !! i sent the message");
    }
}
