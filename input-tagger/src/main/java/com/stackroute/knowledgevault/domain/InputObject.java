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

    public void compress() {
        for (int i = 0; i < lemmas.size()-1; i++) {
            if (poses.get(i+1).equals(poses.get(i))) {
                lemmas.get(i).concat(" " + lemmas.get(i+1));
                lemmas.remove(i+1);
                poses.remove(i+1);
            }
        }
    }
}
