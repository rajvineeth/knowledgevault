package com.stackroute.knowledgevault.populator.service;

import com.stackroute.knowledgevault.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class KafkaConsumer {
    @Autowired
    ReadJsonld readJsonld;
    @Autowired
    MedicalGraphService medicalGraphService;

    @KafkaListener(topics="prod2",groupId = "group_json", containerFactory= "userKafkaListenerFactory")
    public void consumejson(JsonLDObject res){
        System.out.println("consumed message"+res.toString());
//        int id=res.getId();
//        Map<String,Object> root=res.getJsonld();
//        MedicalCondition medicalCondition=readJsonld.getMedicalCondition(root);
//        Anatomy anatomy=readJsonld.getAnatomy(root);
//        List<MedicalSymptom> medicalSymptomList=readJsonld.getSymptoms(root);
        //medicalGraphService.makegraph(id,medicalCondition,anatomy,medicalSymptomList);
    }
    @KafkaListener(topics="kafkaTest",groupId = "group_json", containerFactory= "userKafkaListenerFactory")
    public void consumeFromPara(JSONld res){
        System.out.println("consumed message from para"+res.toString());
//        int id=res.getId();
//        Map<String,Object> root=res.getData();
//        MedicalCondition medicalCondition=readJsonld.getMedicalCondition(root);
//        Anatomy anatomy=readJsonld.getAnatomy(root);
//        List<MedicalSymptom> medicalSymptomList=readJsonld.getSymptoms(root);
        //medicalGraphService.makegraph(id,medicalCondition,anatomy,medicalSymptomList);
    }

}
