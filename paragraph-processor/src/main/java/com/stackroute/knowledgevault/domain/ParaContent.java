package com.stackroute.knowledgevault.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParaContent {
    private int id;
    private int paraId;
    private String data;
}
