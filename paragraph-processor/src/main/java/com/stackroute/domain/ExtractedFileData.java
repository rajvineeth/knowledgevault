package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class ExtractedFileData {
    private int id;
    private String metadata;
    private String content;

    @Override
    public String toString() {
        return "ExtractedFileData{" +
                "id=" + id +
                ", metadata='" + metadata + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
