package com.stackroute.knowledgevault.queryengine.service;

import com.stackroute.knowledgevault.domain.ExtractedFileData;
import com.stackroute.knowledgevault.domain.OutputResult;
import com.stackroute.knowledgevault.domain.ParaContent;
import org.neo4j.driver.v1.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QueryService {

    private ParaContentService paraContentService;
    private ExtractedDataService extractedDataService;
    Set<String> symset;
    Set<String> anaset;
    Set<String> docset;
    Set<String> paraset;
    Set<String> symset2;
    Set<String> anaset2;
    Set<String> docset2;
    Set<String> paraset2;


    private static final Logger LOGGER = LoggerFactory.getLogger(QueryService.class);

    @Autowired
    public QueryService(ParaContentService paraContentService,ExtractedDataService extractedDataService) {
        this.paraContentService = paraContentService;
        this.extractedDataService=extractedDataService;
    }
    public QueryService(){

    }

boolean flag2=false;
    OutputResult outputResult2=null;
    //LinkedHashSet<OutputResult> res2 = new LinkedHashSet<>();
    public LinkedHashSet<OutputResult> runquery2(Driver driver, String k1, String k2) {
        LinkedHashSet<OutputResult> res2 = new LinkedHashSet<>();
        LOGGER.info("received keywords from tagger2");
        if(!res2.isEmpty()){
            res2.clear();
        }
        String q;
        try (Session session = driver.session()) {
            q="MATCH (A:" + k2 + "),p=(B)-[r]-(A) WHERE A.name contains '" + k1 + "' RETURN A.name As name1, labels(A) as name1label, TYPE(r) as ril, B.name As name, labels(B) as label, B.id as docId, B.paraId as paraId LIMIT 500";

            try (Transaction tx = session.beginTransaction()) {
                StatementResult result = tx.run(q);
                while (result.hasNext()) {
                    Record record = result.next();
                    flag2=false;
                    LOGGER.info("query run");
                    for(OutputResult outputResult1:res2){

                        if(outputResult1.getDiseaseName().compareTo(record.get("name1").toString())==0){
                            outputResult2=outputResult1;
                            symset2=outputResult1.getSymptoms();
                            anaset2=outputResult1.getAnatomy();
                            docset2=outputResult1.getDoc();
                            paraset2=outputResult1.getPara();

                            flag2=true;
                        }
                    }
                    if(!flag2){
                        outputResult2 = new OutputResult();
                        symset2=new HashSet<>();
                        anaset2=new HashSet<>();
                        docset2=new HashSet<>();
                        paraset2=new HashSet<>();
                        outputResult2.setDiseaseName(record.get("name1").toString());
                    }
                    String label=record.get("label").toString().substring(1,record.get("label").toString().length()-1);
                    if(label.equals("\"MedicalSymptom\"")){
                        symset2.add(record.get("name").toString());
                    }
                    if(label.equals("\"AnatomicalStructure\"")){
                        anaset2.add(record.get("name").toString());
                    }
                    if(label.equals("\"Document\"")){
                        if(record.get("name").toString().contains("http")){
                            docset2.add(record.get("name").toString());
                        }
                        String[] strlist =  record.get("docId").toString().substring(1,record.get("docId").toString().length()-1).split(",");
                        List<Integer> docs=new ArrayList<>();
                        for(String s:strlist){
                            docs.add(Integer.valueOf(s.trim()));
                        }
                        for(Integer i:docs){
                            docset2.add(getdoc(i));
                        }
                    }
                    if(label.equals("\"Content\"")) {
                        String[] strlist =  record.get("paraId").toString().substring(1,record.get("paraId").toString().length()-1).split(",");
                        List<Integer> para=new ArrayList<>();
                        for(String s:strlist){
                            para.add(Integer.valueOf(s.trim()));
                        }
                        for(Integer i:para){
                            paraset2.add(getpara(i));
                        }
                        Integer doc=  Integer.parseInt(record.get("docId").toString());
                        docset2.add(getdoc(doc));
                    }
                    LOGGER.info("Out of string loop");
                    tx.success();  // Mark this write as successful.
                    outputResult2.setSymptoms(symset2);
                    outputResult2.setAnatomy(anaset2);
                    outputResult2.setDoc(docset2);
                    outputResult2.setPara(paraset2);
                    if(flag2)
                        res2.remove(outputResult2);
                    res2.add(outputResult2);
                }

            }
        }

        return res2;
    }
boolean flag=false;
    OutputResult outputResult=null;
   // LinkedHashSet<OutputResult> res = new LinkedHashSet<>();
    public LinkedHashSet<OutputResult> runquery(Driver driver, String k1, String k2) {
        LinkedHashSet<OutputResult> res = new LinkedHashSet<>();
        LOGGER.info("received keywords from tagger");
        if(!res.isEmpty()){
            res.clear();
        }
        String q;
        try (Session session = driver.session()) {
            q="MATCH (A:" + k2 + "),p=(B:MedicalCondition)-[r]-(A),j=(C)-[s]-(B:MedicalCondition) WHERE A.name contains '" + k1 + "' RETURN A.name As name1, labels(A) as name1label, TYPE(r) as ril, B.name As diseaseName, labels(B) as diseaseLabel, TYPE(s) AS sil, C.name As name3,C.id as docId,C.paraId as paraId, labels(C) As Ctype LIMIT 500";
            try (Transaction tx = session.beginTransaction()) {
                LOGGER.info("query run");

                StatementResult result = tx.run(q);
                while (result.hasNext()) {
                    Record record = result.next();
                    flag=false;

                for(OutputResult outputResult1:res){
                    if(outputResult1.getDiseaseName().compareTo(record.get("diseaseName").toString())==0){
                        outputResult=outputResult1;
                        symset=outputResult1.getSymptoms();
                        anaset=outputResult1.getAnatomy();
                        docset=outputResult1.getDoc();
                        paraset=outputResult1.getPara();

                        flag=true;
                    }
                }
                if(!flag){
                    outputResult = new OutputResult();
                    symset=new HashSet<>();
                    anaset=new HashSet<>();
                    docset=new HashSet<>();
                    paraset=new HashSet<>();
                    outputResult.setDiseaseName(record.get("diseaseName").toString());
                }
                String ctype=record.get("Ctype").toString().substring(1,record.get("Ctype").toString().length()-1);

                if(ctype.equals("\"MedicalSymptom\"")){
                    symset.add(record.get("name1").toString());
                    symset.add(record.get("name3").toString());
                }
                if(ctype.equals("\"AnatomicalStructure\"")){
                    anaset.add(record.get("name3").toString());
                }
                if(ctype.equals("\"Document\"")){
                    String[] strlist =  record.get("docId").toString().substring(1,record.get("docId").toString().length()-1).split(",");
                    List<Integer> docs=new ArrayList<>();
                    for(String s:strlist){
                        docs.add(Integer.valueOf(s.trim()));
                    }
                    for(Integer i:docs){
                        docset.add(getdoc(i));
                    }
                }
                if(ctype.equals("\"Content\"")) {
                    String[] strlist =  record.get("paraId").toString().substring(1,record.get("paraId").toString().length()-1).split(",");
                    List<Integer> para=new ArrayList<>();
                    for(String s:strlist){
                        para.add(Integer.valueOf(s.trim()));
                    }
                    for(Integer i:para){
                        paraset.add(getpara(i));
                    }
                    Integer doc=  Integer.parseInt(record.get("docId").toString());
                    docset.add(getdoc(doc));
                }

                    tx.success();  // Mark this write as successful.
                    outputResult.setSymptoms(symset);
                    outputResult.setAnatomy(anaset);
                    outputResult.setDoc(docset);
                    outputResult.setPara(paraset);
                    if(flag)
                        res.remove(outputResult);
                    res.add(outputResult);
                }
                LOGGER.info("Out of string loop");

            }
        }
        return res;
    }
    public String getpara(int paraId){
        Optional<ParaContent> savedList;
        savedList = paraContentService.getParaById(paraId);
        return savedList.get().getData();
    }
    public String getdoc(Integer id){
        if(id==9999){
            return null;
        }
        Optional<ExtractedFileData> doc=extractedDataService.getDocById(id);
        String content=doc.get().getContent();
        return content;
    }
    public void loadgraph(Driver driver) {
        LOGGER.info("Loading The Graph");
        try (Session session = driver.session()) {
            String g1 = "LOAD CSV WITH HEADERS FROM 'https://gist.githubusercontent.com/anand-jadhav/323d279c7b3ef477c5555a031b00da73/raw/e366c239441db747d507bbc603bb010f5698978c/sym_t.csv' AS line " +
                    "CREATE (def:MedicalSymptom{syd:line.syd,name:line.symptom})";
            String g2 = "LOAD CSV WITH HEADERS FROM 'https://gist.githubusercontent.com/anand-jadhav/f20082c9fd9db159cbd7101713ebfd9e/raw/721f1ccebac5fabfdec023aab3b489e1b9f0af96/dianew.csv' AS line " +
                    "CREATE (def:MedicalCondition{did:line.did,name:line.diagnose})";
            String g3 ="LOAD CSV WITH HEADERS FROM 'https://gist.githubusercontent.com/anand-jadhav/00eb984889a4b73247c336862bf79dfb/raw/286f104715cc41d0d04432f43a744aa1d1dd54d2/diffsydiw.csv' AS line " +
                    "CREATE (def:Matchers{did:line.did,syd:line.syd})";
            String g4 = "MATCH(a:MedicalCondition),(b:MedicalSymptom),(n:Matchers) " +
                    "WHERE n.did=a.did AND n.syd=b.syd " +
                    "CREATE (a)-[r:has_symptom]->(b)";
            String g5= "MATCH(A:Matchers) DELETE A";
            try (Transaction tx = session.beginTransaction()) {
                tx.run(g1);
                tx.run(g2);
                tx.run(g3);
                tx.run(g4);
                tx.run(g5);
                tx.success();  // Mark this write as successful.
                LOGGER.info("Graph Loaded Succesfully");

            }
        }
    }

    public void close(Driver driver) {
        // Closing a driver immediately shuts down all open connections.
        LOGGER.info("Closing Driver Session");
        driver.close();
    }
}
