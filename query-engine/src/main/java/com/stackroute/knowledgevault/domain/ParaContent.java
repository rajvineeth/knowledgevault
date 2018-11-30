package com.stackroute.knowledgevault.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParaContent {

    @Id
    private Integer paraId;
    private Integer docId;
    private String data;
}

