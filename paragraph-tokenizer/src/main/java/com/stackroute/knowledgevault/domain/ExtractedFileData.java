package com.stackroute.knowledgevault.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ExtractedFileData {

    private static Integer count = 0;

    private Integer id;
    private String metadata;
    private String content;

    public ExtractedFileData() {
        this.id = count++;
    }

//    public ExtractedFileData(Integer id, String content) {
//        this.id = id;
//        this.content = content;
//    }
}
