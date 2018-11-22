package com.stackroute.knowledgevault.paragraphprocessor.utilities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pair {
    private String keyword;
    private Double score;
}
