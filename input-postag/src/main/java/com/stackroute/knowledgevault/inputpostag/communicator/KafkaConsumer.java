package com.stackroute.knowledgevault.inputpostag.communicator;

import com.stackroute.knowledgevault.domain.InputPOS;
import com.stackroute.knowledgevault.domain.UserInput;
import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    private KafkaProducer kafkaProducer;

    @KafkaListener(topics="user-input-POS", groupId = "group_json_POS", containerFactory= "documentKafkaListenerFactory")
    public void consumejson(UserInput userInput){
        LOGGER.info("consumed message");

        Document fileinput = new Document(userInput.getText());
        List<String> POSTags = new ArrayList<>();

        for(Sentence sentence: fileinput.sentences()){
            for(int i=0;i<sentence.length();i++){
                POSTags.add(sentence.posTag(i));
            }
        }

        LOGGER.info(kafkaProducer.post(new InputPOS(userInput.getId(), POSTags)));
    }
}
