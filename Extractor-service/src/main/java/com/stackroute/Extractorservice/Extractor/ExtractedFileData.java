package com.stackroute.Extractorservice.Extractor;


public class ExtractedFileData {

    public static Integer count = 0;

    private Integer id;
    private String metadata;
    private Object content;

    public ExtractedFileData(String metadata, Object content) {
        this.metadata = metadata;
        this.content = content;
    }

    public ExtractedFileData() {
        this.id=count++;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}