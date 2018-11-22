package com.stackroute.knowledgevault.domain;

import lombok.*;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Index;
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
    @Index(unique=true, primary=true)
    private String conditionName;
    private String type;


    @Relationship(type="causedBy")
    private List<Cause> causeList;
    @Relationship(type="associatedAnatomy")
    private AnatomicalStructure anatomicalStructure;
    @Relationship(type="distinguishingSign")
    private List<MedicalSymptom> medicalSymptomList;
    @Relationship(type="possibleTreatment")
    private List<Treatment> treatmentList;

    public MedicalCondition(String conditionName,String type){
        this.type=type;
        this.conditionName=conditionName;
    }

}
