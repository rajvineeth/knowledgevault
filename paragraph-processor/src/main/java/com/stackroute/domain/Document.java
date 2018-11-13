package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Model Class that will hold the Document Information
 */

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class Document {

    private int docId;
    private String text;

}
