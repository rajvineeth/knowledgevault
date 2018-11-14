//package com.stackroute.populator.loaders;
//
//import com.github.jsonldjava.core.JsonLdOptions;
//import com.github.jsonldjava.core.JsonLdProcessor;
//import com.github.jsonldjava.utils.JsonUtils;
//import Cause;
//import MedicalCondition;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.stereotype.Component;
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.HashMap;
//import java.util.Map;
//
//@Component
//public class Loaders implements ApplicationListener<ContextRefreshedEvent>{
//    private MedicalCondition medicalCondition;
//    private Cause cause;
//
//    public Loaders(MedicalCondition medicalCondition, Cause cause) {
//        this.medicalCondition = medicalCondition;
//        this.cause = cause;
//    }
//
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//
//        InputStream inputStream = null;
//        try {
//            inputStream = new FileInputStream("populator/src/main/Docs/input.json");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        Object jsonObject = null;
//        try {
//            jsonObject = JsonUtils.fromInputStream(inputStream);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Map context = new HashMap();
//        JsonLdOptions options = new JsonLdOptions();
//        Object compact = JsonLdProcessor.compact(jsonObject, context, options);
//        try {
//            System.out.println(JsonUtils.toPrettyString(compact));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Map<String, Object> root = (Map) jsonObject;
//        String type=(String) root.get("@type");
//        String name=(String)root.get("alternameName");
//        Long id=1L;
//
//        Map<String, String> causeMap = (Map) root.get("cause");
//        String causeName=(String)causeMap.get("name");
//        String causeType=(String)causeMap.get("@type");
//        Long causeId=1L;
//
//        medicalCondition.setCondiationName(name);
//        medicalCondition.setConditionId(id);
//        medicalCondition.setType(type);
//
//        cause.setCauseId(causeId);
//        cause.setCauseName(causeName);
//        cause.setType(causeType);
//    }
//}
