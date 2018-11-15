package com.stackroute.knowledgevault.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

/**
 * POJO class for consuming input sent by Extractor Service.
 */

public class ExtractedFileData {

    @Id
    private int id; //Id of the document
    private String metadata; //Will contain the metadata of the document
    private String content; //Will contain all the content of the document


}
