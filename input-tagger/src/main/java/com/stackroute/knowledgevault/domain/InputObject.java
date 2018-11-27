package com.stackroute.knowledgevault.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputObject {
    private static final Logger LOGGER = LoggerFactory.getLogger(InputObject.class);
    private int id;
    private List<String> lemmas;
    private List<String> poses;

    public InputObject(int id) {
        LOGGER.info("inside InputObject.constructor()");
        this.id = id;
    }
}
