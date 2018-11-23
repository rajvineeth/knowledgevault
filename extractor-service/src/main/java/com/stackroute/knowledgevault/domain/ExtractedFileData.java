package com.stackroute.knowledgevault.domain;

import lombok.*;

@Data
@AllArgsConstructor
public class ExtractedFileData {

    private static Integer count = 0;

    private Integer id; //Id of the document
    private String metadata; //Will contain the metadata of the document
    private String content; //Will contain all the content of the document

    public ExtractedFileData() {
        this.id=count++;
    }

}