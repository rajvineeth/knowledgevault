package com.stackroute.knowledgevault.inputpostag.communicator;

import com.stackroute.knowledgevault.domain.InputPOS;
import com.stackroute.knowledgevault.domain.UserInput;
import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class KafkaConsumer {

    private Logger logger;

    @Autowired
    private KafkaProducer kafkaProducer;

    @KafkaListener(topics="user-input", groupId = "group_json", containerFactory= "documentKafkaListenerFactory")
    public void consumejson(UserInput userInput){
        logger.info("consumed message");

        Document fileinput = new Document(userInput.getText());
        List<String> POSTags = new ArrayList<>();

        for(Sentence sentence: fileinput.sentences()){
            for(int i=0;i<sentence.length();i++){
                POSTags.add(sentence.posTag(i));
            }
        }

        logger.info(kafkaProducer.post(new InputPOS(userInput.getId(), POSTags)));
    }
}
