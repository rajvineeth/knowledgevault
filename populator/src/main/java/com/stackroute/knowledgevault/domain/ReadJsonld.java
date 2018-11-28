package com.stackroute.knowledgevault.domain;

import com.stackroute.knowledgevault.populator.service.ContentService;
import com.stackroute.knowledgevault.populator.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
@Component
public class ReadJsonld {
    @Autowired
    DocumentService documentService;
    @Autowired
    ContentService contentService;

    public MedicalCondition getMedicalCondition(Map jsonObject) {
        Map<String, Object> root = jsonObject;
        if ((String) root.get("alternateName") != null && !(((String) root.get("alternateName")).equals("null")) ) {
            String name =  ((String) root.get("alternateName")).toLowerCase();
            String type = (String) root.get("@type");
            return new MedicalCondition(name, type);
        }
        return null;
    }

    public AnatomicalStructure getAnatomicalStructure(Map jsonObject) {
        Map<String, Object> root = jsonObject;
        Map<String, String> anatomyMap = (Map<String, String>) root.get("associatedAnatomy");
        if (anatomyMap.get("name")!=null && !((anatomyMap.get("name")).equals("null"))) {
            String anatomyName = anatomyMap.get("name").toLowerCase();
            String anatomyType = anatomyMap.get("@type");
            AnatomicalStructure anatomicalStructure = new AnatomicalStructure(anatomyName, anatomyType);
            return anatomicalStructure;
        }
        return null;
    }

    public List<MedicalSymptom> getSymptoms(Map jsonObject) {
        Map<String, Object> root = jsonObject;
        if ((Map<String, List>) root.get("differentialDiagnosis") != null) {
            Map<String, List> differentialDiagnosis = (Map<String, List>) root.get("differentialDiagnosis");
            List<Map<String, String>> distinguishingSignList = differentialDiagnosis.get("distinguishingSign");
            Iterator signIterator = distinguishingSignList.iterator();
            List<MedicalSymptom> medicalSymptomList = new ArrayList<>();
            while (signIterator.hasNext()) {
                Map<String, String> sym = (Map<String, String>) signIterator.next();
                if(sym.get("name")!=null && !((sym.get("name")).equals("null"))) {
                    String symptomName = sym.get("name").toLowerCase();
                    String symptomType = sym.get("@type");
                    MedicalSymptom medicalSymptom = new MedicalSymptom(symptomName, symptomType);
                    medicalSymptomList.add(medicalSymptom);
                }
            }
            return medicalSymptomList;
        }
        return null;
    }

    public Document createDoc(int id, MedicalCondition medicalCondition) {
        Document document;

        boolean exists = false;
        List<Document> documentList = documentService.documentList();
        for (Document doc : documentList) {
            if (doc.getName().equals(medicalCondition.getName())) {
                document = doc;
                ArrayList<Integer> list = document.getId();
                if (list != null) {
                    list.add(id);
                    document.setId(list);
                } else {
                    ArrayList<Integer> list1 = new ArrayList<>();
                    list1.add(id);
                    document.setId(list1);
                }
                exists = true;
                return document;
            }
        }
        if (!exists) {
            ArrayList<Integer> list1 = new ArrayList<>();
            list1.add(id);
            document = new Document(medicalCondition.getName(), "Document", list1);
            return document;
        }
        return null;
    }
    public Content createContent(int id,int paraId, MedicalCondition medicalCondition) {
        Content content;
        if(medicalCondition!=null) {
            boolean exists = false;
            List<Content> contentList = contentService.contentList();
            for (Content content1 : contentList) {
                if (content1.getName().equals(medicalCondition.getName())) {
                    content = content1;
                    ArrayList<Integer> list = content.getParaId();
                    if (list != null) {
                        list.add(paraId);
                        content.setParaId(list);
                    } else {
                        ArrayList<Integer> list1 = new ArrayList<>();
                        list1.add(paraId);
                        content.setParaId(list1);
                    }
                    exists = true;
                    return content;
                }
            }
            if (!exists) {
                ArrayList<Integer> list1 = new ArrayList<>();
                list1.add(paraId);
                content = new Content(medicalCondition.getName(), "Content", id, list1);
                return content;
            }
        }
        return null;
    }

}
