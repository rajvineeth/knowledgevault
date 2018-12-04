package com.stackroute.knowledgevault.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OutputResult {
    @Id
    private String diseaseName;
//    public String Node1label;
//    public String Relation;
    private Set<String> anatomy;
    private Set<String> symptoms;
//    public String Node2label;
private Set<String> para;
private Set<String> doc;


//    public String Node1label;
//    public String Relation;
//    public String Node2label;
//    public String Node1;
//    public String Node2;

}
