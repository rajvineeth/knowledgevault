package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
/**
 * The Model Class that will hold the Document Information
 */

public class ParaDoc {

    private int docId;
    private String text;

}
