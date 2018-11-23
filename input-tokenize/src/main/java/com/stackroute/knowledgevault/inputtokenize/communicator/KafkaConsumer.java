package com.stackroute.knowledgevault.inputtokenize.communicator;

import com.stackroute.knowledgevault.domain.InputToken;
import com.stackroute.knowledgevault.domain.UserInput;
import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.ArrayList;
import java.util.List;

public class KafkaConsumer {

    @Autowired
    private KafkaProducer producer;

    //to log data on the console
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    /*
     * This method consumes data from kafka server and makes call to kafka producer.
     */
    @KafkaListener(topics = "user-input", groupId = "group_json", containerFactory = "documentKafkaListenerFactory")
    public void consumejson(UserInput userInput){
        LOGGER.info("TokenUserInput: {}",userInput.toString());
        Document document = new Document(userInput.getText());
        LOGGER.info("TokenDocument: {}",document.toString());
        List<Sentence> sentences = document.sentences();
        LOGGER.info("TokenSentences: {}",sentences.toString());
        List<String> tokens = new ArrayList<>();
        List<String> temp;
        for (Sentence sentence: sentences) {
            temp = sentence.words();
            LOGGER.info("TokenTempLemma: {}",temp.toString());
            for (String token: temp) {
                tokens.add(token);
            }
        }
        LOGGER.info("Tokenfinal: {}",tokens.toString());
        producer.post(new InputToken(userInput.getId(),tokens));
    }
}
