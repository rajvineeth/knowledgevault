package com.stackroute.knowledgevault.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputObject {

    private int id;
    private List<String> lemmas;
    private List<String> poses;

    public InputObject(int id) {
        this.id = id;
    }

}
