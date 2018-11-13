package com.stackroute.knowledgevault.algos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AhoCorasick {

    private static final Logger LOGGER = LoggerFactory.getLogger(AhoCorasick.class);
    static final int ALPHABET_SIZE = 26;

    public Node[] nodes;
    public int nodeCount;

    public static class Node {
        int parent;
        char charFromParent;
        int suffLink = -1;
        int[] children = new int[ALPHABET_SIZE];
        int[] transitions = new int[ALPHABET_SIZE];
        public boolean leaf;

        {
            Arrays.fill(children, -1);
            Arrays.fill(transitions, -1);
        }
    }

    public AhoCorasick() {}

    public AhoCorasick(int maxNodes) {
        nodes = new Node[maxNodes];
        // create root
        nodes[0] = new Node();
        nodes[0].suffLink = 0;
        nodes[0].parent = -1;
        nodeCount = 1;
    }

    public boolean addString(String s) {
        boolean res;
        int cur=0;
        try {
            for (char ch : s.toCharArray()) {
                int c = ch - 'a';
                if (nodes[cur].children[c] == -1) {
                    nodes[nodeCount] = new Node();
                    nodes[nodeCount].parent = cur;
                    nodes[nodeCount].charFromParent = ch;
                    nodes[cur].children[c] = nodeCount++;
                }
                cur = nodes[cur].children[c];
            }
            nodes[cur].leaf = true;
        }
        catch(Exception e) {
            LOGGER.debug("something went wrong...");
        }
        res = nodes[cur].leaf;
        return res;
    }

    public int suffLink(int nodeIndex) {
        Node node = nodes[nodeIndex];
        if (node.suffLink == -1)
            node.suffLink = node.parent == 0 ? 0 : transition(suffLink(node.parent), node.charFromParent);
        return node.suffLink;
    }

    public int transition(int nodeIndex, char ch) {
        int c = ch - 'a';
        Node node = nodes[nodeIndex];
        if (node.transitions[c] == -1)
            node.transitions[c] = node.children[c] != -1 ? node.children[c] : (nodeIndex == 0 ? 0 : transition(suffLink(nodeIndex), ch));
        return node.transitions[c];
    }

    public List<Integer> solver(AhoCorasick ahoCorasick,String txt) {
        int node = 0;
        List<Integer> positions = new ArrayList<>();
        for (int i = 0; i < txt.length(); i++) {
            node = ahoCorasick.transition(node, txt.charAt(i));
            if (ahoCorasick.nodes[node].leaf)
                positions.add(i);
        }
        LOGGER.info("position: {}\n",positions);
        return positions;
    }

}
