package com.stackroute.knowledgevault.inputprocessor.listener;

import com.stackroute.knowledgevault.domain.Input;
import com.stackroute.knowledgevault.domain.Word;
import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaConsumer {

    private Input input;
    private List sentences;

    @KafkaListener(topics="kafka_example", groupId ="group_id")
    public void consume(String message){
        System.out.println("consumed message:"+message);
    }
    @KafkaListener(topics="kafka_example_jsonh",groupId = "group_json", containerFactory= "documentKafkaListenerFactory")
    public void consumejson(Input INPUT){
        this.input = INPUT;
        Document document = new Document(INPUT.getText());
        List<Sentence> sentences = document.sentences();

        for (Sentence sentence : sentences) {
            List words = sentence.words();

        }

        System.out.println("consumed message"+INPUT.toString());
    }
    public Input getInput() {
        return input;
    }
}
