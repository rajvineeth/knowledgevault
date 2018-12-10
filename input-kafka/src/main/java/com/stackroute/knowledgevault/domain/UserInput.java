package com.stackroute.knowledgevault.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInput {
    private static int count = 0;
    private int id;
    private String text;

    public UserInput(String text) {
        this.id = ++count;
        this.text = text;
    }
}
