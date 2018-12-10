package com.stackroute.knowledgevault.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JSONld {

    private int id;
    private int paraId;
    private Map<String,Object> data;
}
