package com.stackroute.populator.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NodeEntity
public class Treatment {
    @Id
    private Long treatmentId;
    private String type;
    private String treatmentName;
    //private ArrayList<Map<String,String>> dose;
    private String doseUnit;
    private String frequency;

}
