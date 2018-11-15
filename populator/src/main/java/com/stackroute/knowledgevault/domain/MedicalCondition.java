package com.stackroute.knowledgevault.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NodeEntity
public class MedicalCondition {
    @Id
    private Long conditionId;
    private String type;
    private String conditionName;

    @Relationship(type="causedBy")
    private List<Cause> causeList;
    @Relationship(type="associatedAnatomy")
    private Anatomy anatomy;
    @Relationship(type="distinguishingSign")
    private List<Symptom> symptomList;
    @Relationship(type="possibleTreatment")
    private List<Treatment> treatmentList;

//    public MedicalCondition(Long conditionId,String type,String conditionName){
//        this.conditionId=conditionId;
//        this.type=type;
//        this.conditionName=conditionName;
//    }
}
