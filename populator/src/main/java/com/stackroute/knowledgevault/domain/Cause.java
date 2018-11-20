package com.stackroute.knowledgevault.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Index;
import org.neo4j.ogm.annotation.NodeEntity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NodeEntity
public class Cause {

    @Id
    @Index(unique=true, primary=true)
    private String causeName;
    private String type;


//
//    public Cause(Long causeId,String type,String causeName){
//        this.causeId=causeId;
//        this.type=type;
//        this.causeName=causeName;
//    }
}
