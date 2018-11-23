package com.stackroute.knowledgevault.paragraphprocessor.algos;

import java.util.List;

/**
 * This interface provides tokenization implementation for a text file
 */
public interface Tokenizer {

    List<String> tokenize(String text);
}
