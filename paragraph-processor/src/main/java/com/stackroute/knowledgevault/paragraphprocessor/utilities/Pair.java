package com.stackroute.knowledgevault.paragraphprocessor.utilities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * This Utility class manages a <key,value> pair which contains a keyword and its score in the document.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pair {

    private String keyword;
    private Double score;

}
