package com.stackroute.knowledgevault.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Input Data object generated by Extractor-Service.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtractedFileData {

    private Integer id;
    private String metadata;
    private String content;

}
