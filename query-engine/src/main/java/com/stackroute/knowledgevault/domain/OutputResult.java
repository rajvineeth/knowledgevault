package com.stackroute.knowledgevault.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OutputResult {
    private String diseaseName;
//    public String Node1label;
//    public String Relation;
    private List<String> anatomy;
    private List<String> symptoms;
//    public String Node2label;
private List<String> para;
private List<String> doc;


    public String Node1label;
    public String Relation;
    public String Node2label;
    public String Node1;
    public String Node2;

}
