package com.stackroute.knowledgevault.populator.service;

import com.stackroute.knowledgevault.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class KafkaConsumer {
    @Autowired
    ReadJsonld readJsonld;
    @Autowired
    MedicalGraphService medicalGraphService;
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics="prod2",groupId = "group_json", containerFactory= "userKafkaListenerFactory")
    public void consumejson(JsonLDObject res) {
        if(res!=null) {
            LOGGER.info("consumed message : {} ", res);
            int id = res.getId();
            Map<String, Object> root = res.getJsonld();
            MedicalCondition medicalCondition = readJsonld.getMedicalCondition(root);
            AnatomicalStructure anatomicalStructure = readJsonld.getAnatomicalStructure(root);
            List<MedicalSymptom> medicalSymptomList = readJsonld.getSymptoms(root);
            Document document=readJsonld.createDoc(id,medicalCondition);
            medicalGraphService.populate(document, medicalCondition, anatomicalStructure, medicalSymptomList);
        }
    }
    @KafkaListener(topics="kafkaTest",groupId = "group_json", containerFactory= "userKafkaListenerFactory2")
    public void consumeFromPara(JSONld res) {
        if(res!=null) {
            LOGGER.info("consumed message from para {}", res);
            int id = res.getId();
            int paraId=res.getParaId();
            Map<String, Object> root = res.getData();
            MedicalCondition medicalCondition = readJsonld.getMedicalCondition(root);
            AnatomicalStructure anatomicalStructure = readJsonld.getAnatomicalStructure(root);
            List<MedicalSymptom> medicalSymptomList = readJsonld.getSymptoms(root);
            Content content=readJsonld.createContent(id,paraId,medicalCondition);
            medicalGraphService.populateFromPara(content, medicalCondition, anatomicalStructure, medicalSymptomList);
        }
    }

    @KafkaListener(topics="scraperOutput",groupId = "group_json", containerFactory= "userKafkaListenerFactory3")
    public void consumeFromAdapter(ScrapedData res) {
        if(res!=null) {
            LOGGER.info("consumed message from para {}", res);
            ArrayList<Integer> id=new ArrayList<>();
            id.add(9999);
            MedicalCondition medicalCondition = new MedicalCondition(res.getTitle().toLowerCase(),"MedicalCondition");
            Document document=new Document(res.getUrl(),"Document",id);
            List<MedicalSymptom> medicalSymptomList=new ArrayList<>();
            medicalGraphService.populate(document, medicalCondition, new AnatomicalStructure(),medicalSymptomList);
        }
    }

}
