package com.stackroute.knowledgevault.paragraphtokenizer.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Document {
    private int docId;
    private String text;
}
