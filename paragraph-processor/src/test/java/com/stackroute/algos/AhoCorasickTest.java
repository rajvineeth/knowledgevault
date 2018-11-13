package com.stackroute.algos;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.*;

public class AhoCorasickTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AhoCorasickTest.class);
    private AhoCorasick ahoCorasick;

    @Before
    public void init() {
        this.ahoCorasick = new AhoCorasick(1000);
    }

    @After
    public void destroy() {
        this.ahoCorasick = null;
    }

    @Test
    public void solverTest() {
        ArrayList<Integer> pos = new ArrayList<Integer>(){{
           add(3);
           add(5);
        }};

        this.ahoCorasick.addString("bc");
        this.ahoCorasick.addString("abc");

        String s = "tabcbc";

        assertEquals(pos,this.ahoCorasick.solver(this.ahoCorasick,s));
    }
}