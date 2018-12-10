package com.stackroute.knowledgevault.inputlemmatize.communicator;

import com.stackroute.knowledgevault.domain.InputLemma;
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

    @Autowired
    private KafkaProducer producer;

    //to log data on the console
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    /*
     * This method consumes data from kafka server and makes call to kafka producer.
     */
    @KafkaListener(topics = "user-input-lemma", groupId = "group_json_lemma", containerFactory = "documentKafkaListenerFactory")
    public void consumejson(UserInput userInput){
        LOGGER.info("LemmaUserInput: {}",userInput.toString());
        Document document = new Document(userInput.getText());
        LOGGER.info("LemmaDocument: {}",document.toString());
        List<Sentence> sentences = document.sentences();
        LOGGER.info("LemmaSentences: {}",sentences.toString());
        List<String> lemmas = new ArrayList<>();
        List<String> temp;
        for (Sentence sentence: sentences) {
            temp = sentence.lemmas();
            LOGGER.info("LemmaTempLemma: {}",temp.toString());
            for (String lemma: temp) {
                lemmas.add(lemma);
            }
        }
        LOGGER.info("Lemmafinal: {}",lemmas.toString());
        producer.post(new InputLemma(userInput.getId(),lemmas));
    }
}
