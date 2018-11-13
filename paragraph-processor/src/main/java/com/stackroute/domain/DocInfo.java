package com.stackroute.domain;

import java.util.HashMap;
import java.util.Map;

public class DocInfo {

    private static Map<Integer,String> loc;

    public DocInfo() {
        loc  = new HashMap<>();
    }

    public static Map<Integer, String> getLoc() {
        return DocInfo.loc;
    }

    public static void setLoc(Map<Integer, String> loc) {
        loc = loc;
    }

    public static void add(int id,String name) {
        loc.put(id,name);
    }





}
