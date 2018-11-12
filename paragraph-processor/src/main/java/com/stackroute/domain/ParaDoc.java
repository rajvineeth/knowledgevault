package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder

/**
 * The Model Class that will hold the Document Information
 */

public class ParaDoc {

    private int docId;
    private String diseaseName;
    private String symptom;

}
