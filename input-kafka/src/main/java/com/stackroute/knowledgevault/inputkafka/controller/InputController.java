package com.stackroute.knowledgevault.inputkafka.controller;

import com.stackroute.knowledgevault.domain.UserInput;
import com.stackroute.knowledgevault.inputkafka.producer.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/kv" )
public class InputController {
    private static final Logger LOGGER = LoggerFactory.getLogger(InputController.class);

    @Autowired
    private KafkaProducer producer;

    @GetMapping("/{path}")
    public ResponseEntity<?> getInput(@PathVariable("path") String path) {

        UserInput userInput = new UserInput(path);
        LOGGER.info(userInput.toString());

        producer.post(userInput);

        return new ResponseEntity<>(userInput, HttpStatus.OK);
    }

}
