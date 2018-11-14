package com.stackroute.knowledgevault.services;

import edu.emory.mathcs.backport.java.util.Arrays;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StopwordRemovalTest {

    StopwordRemoval stopwordRemoval;

    @Before
    public void setUp() throws Exception {
        stopwordRemoval = new StopwordRemoval();

    }

    @Test
    public void readStopWordsTest() {
        List<String> actualAnswer = stopwordRemoval.readStopWords("testfile.txt");
        String[] expectedAnswer = {"!!", "?!", "??", "!?", "`", "``", "''", "-lrb-", "-rrb-", "-lsb-", "-rsb-", ",", ".", ":", ";","--","|", "/"};
        Assert.assertArrayEquals(actualAnswer.toArray(), expectedAnswer);
    }

    @Test
    public void removeStopwordsTest() {
        String[] terms = {"hi", "this", "is", "awesome"};
        List<String> actualAnswer = stopwordRemoval.removeStopwords(new ArrayList<String>(Arrays.asList(terms)));
        String[] expectedAnswer = {"hi", "awesome"};
        Assert.assertArrayEquals(actualAnswer.toArray(), expectedAnswer);
    }

    @After
    public void tearDown() throws Exception {
        stopwordRemoval = null;
    }
}
