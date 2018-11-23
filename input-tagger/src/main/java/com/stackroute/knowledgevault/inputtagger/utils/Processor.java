package com.stackroute.knowledgevault.inputtagger.utils;

import com.stackroute.knowledgevault.domain.InputObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Processor {

    private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class);

    public Map<String, String> process (InputObject inputObject) {
        LOGGER.info("\n********\nInputObject\n*******\n{}",inputObject.toString());
        return null;
    }
}
