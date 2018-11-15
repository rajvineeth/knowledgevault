package com.stackroute.knowledgevault.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

/**
 * POJO class to produce output for Populator service.
 */
public class OutputForDoc {
    @Id
    private int id;

    private String docTitle;

    private List<String> keywords;
}
