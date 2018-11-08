package com.stackroute.Extractorservice.Extractor;


public class ExtractedFileData {

    private String metadata;
    private Object content;

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

}