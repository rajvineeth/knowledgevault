package com.stackroute.knowledgevault.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data object to be sent to next microservice after processing input.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Document {

    private int id;
    private String text;
}
