package com.stackroute.knowledgevault.inputtagger.utils;

import com.stackroute.knowledgevault.domain.InputObject;
import com.stackroute.knowledgevault.domain.ProcessedInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Processor {

    private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class);

    public ProcessedInput process (InputObject inputObject) {
        LOGGER.info("\n********\nInputObject\n*******\n{}",inputObject.toString());
        Map<String, String> map = new HashMap<>();
        map.put("hand","body-part");
        map.put("cough","symptom");
        map.put("shxassai", "medicine");
        LOGGER.info("output :{}", new ProcessedInput(map));
        return new ProcessedInput(map);
    }
}