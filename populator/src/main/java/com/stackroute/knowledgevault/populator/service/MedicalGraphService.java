package com.stackroute.knowledgevault.populator.service;

import com.github.jsonldjava.utils.JsonUtils;
import com.stackroute.knowledgevault.domain.*;
import com.stackroute.knowledgevault.populator.repository.MTRRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MedicalGraphService {
    @Autowired
    private MTRRepo mtrRepo;
    @Autowired
    private MedicalConditionService medicalConditionService;
    @Autowired
    private AnatomyService anatomyService;
    @Autowired
    private SymptomService symptomService;
    @Autowired
    private DocumentService documentService;
    @Autowired
    private ContentService contentService;
    @Autowired
    ReadJsonld readJsonld;

    public MedicalGraphService(MTRRepo mtrRepo, MedicalConditionService medicalConditionService, AnatomyService anatomyService, SymptomService symptomService, DocumentService documentService, ContentService contentService) {
        this.mtrRepo = mtrRepo;
        this.medicalConditionService = medicalConditionService;
        this.anatomyService = anatomyService;
        this.symptomService = symptomService;
        this.documentService = documentService;
        this.contentService = contentService;
    }
    public void input(String input, int id) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        InputStream inputStream = new FileInputStream("/knowledge-vault/populator/src/main/Docs/"+input+".json");
        Object jsonObject = JsonUtils.fromInputStream(inputStream);
        Map context = new HashMap();
        Map<String, Object> root = (Map) jsonObject;
        MedicalCondition medicalCondition=readJsonld.getMedicalCondition(root);
        Document document=readJsonld.createDoc(id,medicalCondition);
        Content content=readJsonld.createContent(id,id,medicalCondition);
        List<MedicalSymptom> medicalSymptomList =readJsonld.getSymptoms(root);
        populate(document,medicalCondition, new AnatomicalStructure(), medicalSymptomList);
        populateFromPara(content, medicalCondition, new AnatomicalStructure(), medicalSymptomList);
    }

    public Boolean populate(Document document, MedicalCondition medicalCondition, AnatomicalStructure anatomicalStructure, List<MedicalSymptom> medicalSymptomList)  {
        QueryDriverService example = new QueryDriverService("bolt://localhost", "neo4j", "123456");
        if(medicalCondition!=null && medicalCondition.getName()!=null && medicalCondition.getType()!=null && document!=null) {
            medicalConditionService.saveCondition(medicalCondition);
            documentService.saveDocument(document);
            MTR mtrCD=mtrRepo.getRelation(medicalCondition.getType(),"Document");
            example.createRel(medicalCondition,mtrCD.getType(),document);
        }
        if(anatomicalStructure!=null && anatomicalStructure.getName()!=null && anatomicalStructure.getType()!=null) {
            anatomyService.saveAnatomy(anatomicalStructure);
        }
        if(!medicalSymptomList.isEmpty() && medicalSymptomList.get(0).getName()!=null && medicalSymptomList.get(0).getType()!=null){
            for (MedicalSymptom medicalSymptom : medicalSymptomList) {
                symptomService.saveSymptom(medicalSymptom);
            }
        }
        if(medicalCondition!=null && !medicalSymptomList.isEmpty() && medicalCondition.getName()!=null && medicalSymptomList.get(0).getName()!=null  && medicalSymptomList.get(0).getType()!=null) {
            MTR mtrCS = mtrRepo.getRelation(medicalCondition.getType(), medicalSymptomList.get(0).getType());
            MTR mtrSC = mtrRepo.getRelation(medicalSymptomList.get(0).getType(), medicalCondition.getType());
            for (MedicalSymptom medicalSymptom : medicalSymptomList) {
                String rel1 = "";
                rel1 = mtrCS.getType();
                example.createRel(medicalCondition, rel1, medicalSymptom);
                String rel2 = "";
                rel2 = mtrSC.getType();
                example.createRel(medicalSymptom, rel2, medicalCondition);

            }
        }
        if(medicalCondition!=null && anatomicalStructure!=null && medicalCondition.getName()!=null && anatomicalStructure.getName()!=null && medicalCondition.getType()!=null && anatomicalStructure.getType()!=null) {
            MTR mtrAC = mtrRepo.getRelation(anatomicalStructure.getType(), medicalCondition.getType());
            MTR mtrCA = mtrRepo.getRelation(medicalCondition.getType(), anatomicalStructure.getType());
            String relAC="";
            String relCA="";
            relAC=mtrAC.getType();
            relCA=mtrCA.getType();
            example.createRel(anatomicalStructure,relAC,medicalCondition);
            example.createRel(medicalCondition,relCA, anatomicalStructure);
        }
        example.close();
        return true;
    }
    public Boolean populateFromPara(Content content, MedicalCondition medicalCondition, AnatomicalStructure anatomicalStructure, List<MedicalSymptom> medicalSymptomList)  {
        QueryDriverService example = new QueryDriverService("bolt://localhost", "neo4j", "123456");
        if(medicalCondition!=null && !medicalCondition.getName().equals("null") && !medicalCondition.getType().equals("null")) {
                medicalConditionService.saveCondition(medicalCondition);
            contentService.saveContent(content);
            MTR mtrCC = mtrRepo.getRelation(medicalCondition.getType(), "Content");
            example.createRel(medicalCondition,mtrCC.getType(),content);

        }
        if(anatomicalStructure!=null && anatomicalStructure.getName()!=null && anatomicalStructure.getType()!=null) {
            anatomyService.saveAnatomy(anatomicalStructure);
        }
        if(!medicalSymptomList.isEmpty() && medicalSymptomList.get(0).getName()!=null && medicalSymptomList.get(0).getType()!=null){
            for (MedicalSymptom medicalSymptom : medicalSymptomList) {
                symptomService.saveSymptom(medicalSymptom);
            }
        }
        if(medicalCondition!=null && !medicalSymptomList.isEmpty() && medicalCondition.getName()!=null && medicalSymptomList.get(0).getName()!=null  && medicalSymptomList.get(0).getType()!=null) {
            MTR mtrCS = mtrRepo.getRelation(medicalCondition.getType(), medicalSymptomList.get(0).getType());
            MTR mtrSC = mtrRepo.getRelation(medicalSymptomList.get(0).getType(), medicalCondition.getType());
            for (MedicalSymptom medicalSymptom : medicalSymptomList) {
                String rel1 = "";
                rel1 = mtrCS.getType();
                example.createRel(medicalCondition, rel1, medicalSymptom);
                String rel2 = "";
                rel2 = mtrSC.getType();
                example.createRel(medicalSymptom, rel2, medicalCondition);

            }
        }
        if(medicalCondition!=null && anatomicalStructure!=null && medicalCondition.getName()!=null && anatomicalStructure.getName()!=null && medicalCondition.getType()!=null && anatomicalStructure.getType()!=null) {
            MTR mtrAC = mtrRepo.getRelation(anatomicalStructure.getType(), medicalCondition.getType());
            MTR mtrCA = mtrRepo.getRelation(medicalCondition.getType(), anatomicalStructure.getType());
            String relAC="";
            String relCA="";
            relAC=mtrAC.getType();
            relCA=mtrCA.getType();
            example.createRel(anatomicalStructure,relAC,medicalCondition);
            example.createRel(medicalCondition,relCA, anatomicalStructure);
        }
        example.close();
        return true;
    }

}
