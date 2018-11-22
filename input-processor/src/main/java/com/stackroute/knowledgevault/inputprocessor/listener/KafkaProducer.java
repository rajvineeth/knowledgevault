package com.stackroute.knowledgevault.inputprocessor.listener;


import com.stackroute.knowledgevault.domain.Input;
import com.stackroute.knowledgevault.domain.JSONld;
import com.stackroute.knowledgevault.inputprocessor.service.AppService;
import com.stackroute.knowledgevault.inputprocessor.utilities.FillUpData;
import com.stackroute.knowledgevault.inputprocessor.utilities.Processor;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Component
@RestController
@RequestMapping(value = "/kv/input-processor")
public class KafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, JSONld> kafkaTemplate;

    @Autowired
    private AppService service;

    @Autowired
    private Processor processor;

    private static final String TOPIC = "kafkaTest";

    /* Fetches all the files from the specified folder in path */
    @GetMapping("/{path}")
    public ResponseEntity<?> displayAllFiles(@PathVariable("path") String path) {

        Input input = service.getInput(path); //Fetching all files from the specified path

        post(input);
        return new ResponseEntity<Input>(input, HttpStatus.OK);
    }

    public String post(Input INPUT) {
        LOGGER.info("consumed message: {}",INPUT.toString());
        processor = new Processor();
        JSONObject obj = FillUpData.fill(processor.paraProcessing(INPUT.getText()));
        LOGGER.info("******####******\n\nJSONObject:-\n{}\n\n*****####*****", obj);
        try {
            this.kafkaTemplate.send(TOPIC, new JSONld(obj));
        }catch (Exception e) {
            e.printStackTrace();
        }
        processor = null;
        LOGGER.info("producer message: hey !! i sent the message");
        return "Published successfully";
    }
}
