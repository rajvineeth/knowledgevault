package com.stackroute.knowledgevault.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Map;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JsonLDObject {
    @Id
    private int id;

    private Map<String, Object> jsonld;
}
