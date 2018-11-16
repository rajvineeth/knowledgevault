package com.stackroute.knowledgevault.inputprocessor.listener;

import com.stackroute.knowledgevault.domain.Input;
import com.stackroute.knowledgevault.domain.Word;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class KafkaConsumerTest {

    Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerTest.class);
    KafkaConsumer kafkaConsumer;

    @Before
    public void setUp() throws Exception {
        kafkaConsumer = new KafkaConsumer();
    }

    @After
    public void tearDown() throws Exception {
        kafkaConsumer = null;
    }

    @Test
    public void tokenize() {
        Input input = new Input("My name is Shubham Dutta and I am 24 years old. Since last month, I have been experiencing excruciating pain time to time in my abdomen. And for a week now, a lot of my hair are falling. I have blisters on my back. I sweat a lot in my sleep.");
        List<Word> words = new ArrayList<>();
        words = kafkaConsumer.tokenize(input);

        for (Word word: words){
            LOGGER.info("word list:- \n {}",word);
        }

        assertNotNull(kafkaConsumer.tokenize(input));
    }
}