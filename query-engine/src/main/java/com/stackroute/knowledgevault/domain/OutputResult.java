package com.stackroute.knowledgevault.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OutputResult {
    public String Node1;
    public String Node1label;
    public String Relation;
    public String Node2;
    public String Node2label;

}
