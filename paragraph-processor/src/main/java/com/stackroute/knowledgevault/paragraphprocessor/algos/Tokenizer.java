package com.stackroute.knowledgevault.paragraphprocessor.algos;

import java.util.List;

public interface Tokenizer {

    List<String> tokenize(String text);
}
