package com.stackroute.knowledgevault.inputprocessor.listener;

import com.stackroute.knowledgevault.domain.Input;
import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaConsumer {

    private Input input;
    private List<Sentence> sentences;

    @KafkaListener(topics="kafka_example_jsonh",groupId = "group_json", containerFactory= "documentKafkaListenerFactory")
    public void consumejson(Input INPUT){
        int sentCount = 0;
        int wordCount;

        this.input = INPUT;
        Document document = new Document(INPUT.getText());
        sentences = document.sentences();

        for (Sentence sentence : sentences) {
            List<String> words = sentence.words();
            for (String token: words) {

            }
        }

        System.out.println("consumed message"+INPUT.toString());
    }
    public Input getInput() {
        return input;
    }
}
