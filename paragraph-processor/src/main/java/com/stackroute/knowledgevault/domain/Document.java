package com.stackroute.knowledgevault.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Model Class that will hold the Document Information
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Document {
    private int Id;
    private String text;

}
