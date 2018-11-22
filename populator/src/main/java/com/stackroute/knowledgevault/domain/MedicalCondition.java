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
    private String name;
    private String type;
}
