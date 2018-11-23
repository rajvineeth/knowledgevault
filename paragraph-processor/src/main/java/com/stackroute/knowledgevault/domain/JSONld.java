package com.stackroute.knowledgevault.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JSONld {
    private int id;
    public static int paraId = 1;

    public JSONld(int id,int pid,Map<String,Object> data) {
        this.id = id;
        paraId = pid;
        this.data = data;
    }

    private Map<String,Object> data;
}
