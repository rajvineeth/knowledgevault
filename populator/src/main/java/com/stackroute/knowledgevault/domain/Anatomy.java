package com.stackroute.knowledgevault.domain;

import lombok.*;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NodeEntity
public class Anatomy {

    @Id
    @Index(unique=true, primary=true)
    private String anatomyName;
    private String type;

//    public Anatomy(String anatomyName,String type) {
//        this.type = type;
//        this.anatomyName = anatomyName;
//    }
}
