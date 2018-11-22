package com.stackroute.knowledgevault.paragraphprocessor.algos;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TokenizerImpl implements Tokenizer{

    private Pattern regex;

    public TokenizerImpl(Pattern regex) {
        this.regex = regex;
    }

    public TokenizerImpl() {
        this(Pattern.compile("\\b\\w\\w+"));
    }

    @Override
    public List<String> tokenize(String text) {
        List<String> tokens = new ArrayList<>();
        Matcher matcher = regex.matcher(text);
        while (matcher.find()) {
            tokens.add(matcher.group());
        }
        System.out.println(tokens.toString());
        return tokens;
    }
}
