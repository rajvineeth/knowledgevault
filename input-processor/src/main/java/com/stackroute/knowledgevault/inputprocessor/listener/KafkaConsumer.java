package com.stackroute.knowledgevault.inputprocessor.listener;

import com.stackroute.knowledgevault.domain.Input;
import com.stackroute.knowledgevault.domain.Word;
import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaConsumer {

    private Input input;
    private List<Sentence> sentences;
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics="kafka_example_jsonh",groupId = "group_json", containerFactory= "documentKafkaListenerFactory")
    public void consumejson(Input INPUT){

        List<Word> list = tokenize(INPUT);

    }

    public List tokenize(Input input) {
        int sentCount = 1;
        int wordCount;

        List<Word> words = new ArrayList<>();

        this.input = input;
        Document document = new Document(input.getText());
        this.sentences = document.sentences();

        for (Sentence sentence : this.sentences) {
            wordCount = 1;

            List<String> tokens = sentence.words();
            List<String> lemmas = sentence.lemmas();
            List<String> posTags = sentence.posTags();

            for (int i = 0; i < tokens.size(); i++) {
                words.add(new Word(sentCount, wordCount++, tokens.get(i), lemmas.get(i), posTags.get(i)));
            }
            sentCount++;
        }
//        LOGGER.info("List of words: {}",words);
        return words;
    }

    public Input getInput() {
        return this.input;
    }
}
