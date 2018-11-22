package com.stackroute.knowledgevault.paragraphprocessor.communicators;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.knowledgevault.domain.Document;
import com.stackroute.knowledgevault.domain.JSONld;
import com.stackroute.knowledgevault.paragraphprocessor.utilities.DocProcessor;
import com.stackroute.knowledgevault.paragraphprocessor.utilities.FillUpData;
import com.stackroute.knowledgevault.paragraphprocessor.utilities.Pair;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

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
<<<<<<< HEAD

        Map<String,List<Pair>> tags = new HashMap<>();

        Map<String,Double> keys = DocProcessor.performNGramAnalysis(data.getText());
        LOGGER.info("returned keys: {}",keys.keySet());

        for(Map.Entry<String,Double> key: keys.entrySet()) {

=======
        Map<String,String> tags = new HashMap<>();
        String para = data.getText();
        String[] keys = para.trim().split("\\.|\\s+");
        for(String key:keys) {
>>>>>>> f9890a61d7132c3efeb82cc9eddd0b3065d86b3d
            File dictionary = new File("../../knowledge-vault/paragraph-processor/assets/taggerResource/");
            for(File f: dictionary.listFiles()) {

                tags.putIfAbsent(f.getName(),new ArrayList<>());
                try(BufferedReader br = new BufferedReader(new FileReader(f))) {
                    String txt;
                    while((txt = br.readLine())!=null) {
                        if(txt.compareTo(key.getKey())==0) {
                            tags.get(f.getName()).add(new Pair(key.getKey(),key.getValue()));
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        LOGGER.info(tags.toString());
        JSONObject obj = FillUpData.fillOntology(tags);
        ObjectMapper mapper = new ObjectMapper();
        JSONld jsoNld = null;
        try {
            HashMap<String,Object> map_data = mapper.readValue(obj.toString(), HashMap.class);
            jsoNld = new JSONld(data.getId(),map_data);
            this.producer.post(jsoNld);
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOGGER.info("jsonld object: {}",jsoNld.getData());
        LOGGER.info("producer message: hey !! i sent the message");
    }
}
