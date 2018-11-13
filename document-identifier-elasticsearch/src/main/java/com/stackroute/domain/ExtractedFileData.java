package com.stackroute.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExtractedFileData {

    private Integer id;
    private String metadata;
    private String content;



    public String getId() {
        return String.valueOf(id);
    }

}
