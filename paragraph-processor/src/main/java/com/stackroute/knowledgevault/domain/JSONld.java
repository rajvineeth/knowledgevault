package com.stackroute.knowledgevault.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JSONld {

    private JSONObject obj;
}
