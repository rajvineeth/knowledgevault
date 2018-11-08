package com.stackroute.knowledgevault.paragraphtokenizer.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class KvMessage {

    private final Integer id;

    private final String message;

    @JsonCreator

    public KvMessage(@JsonProperty("id") Integer id,
                     @JsonProperty("message") String message) {
        this.id = id;
        this.message = message;
    }

    public Integer getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "KvMessage{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}
