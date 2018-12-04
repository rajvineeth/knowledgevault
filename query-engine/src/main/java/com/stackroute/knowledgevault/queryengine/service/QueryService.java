package com.stackroute.knowledgevault.queryengine.service;

import com.stackroute.knowledgevault.domain.ExtractedFileData;
import com.stackroute.knowledgevault.domain.OutputResult;
import com.stackroute.knowledgevault.domain.ParaContent;
import org.neo4j.driver.v1.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class QueryService {

    @Autowired
    private ParaContentService paraContentService;
    @Autowired
    private ExtractedDataService extractedDataService;

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryService.class);
//    ParaContentService paraContentService;
//    ExtractedDataService extractedDataService;
    @Autowired
    OutputResult outputResult;
 //   @Autowired
//    public QueryService(ParaContentService paraContentService,ExtractedDataService extractedDataService) {
//        this.paraContentService = paraContentService;
//        this.extractedDataService=extractedDataService;
//    }
//    public QueryService(){
//
//    }

    public List<OutputResult> runquery(Driver driver, String k1, String k2) {
        LOGGER.info("received keywords from tagger");
        Map<String, String> map = new HashMap<>();
        List<OutputResult> res = new ArrayList();

        // Sessions are lightweight and disposable connection wrappers.
        try (Session session = driver.session()) {
            // Wrapping Cypher in an explicit transaction provides atomicity
            // and makes handling errors much easier.
            String q = "MATCH (A:" + k2 + "),p=(B)-[r]-(A) WHERE A.name contains '" + k1 + "' RETURN A.name As name1, labels(A) as name1label, TYPE(r) as ril, B.name As name2, labels(B) as name2label LIMIT 10";
            try (Transaction tx = session.beginTransaction()) {
                OutputResult outputResult = new OutputResult();
                LOGGER.info(q);
                StatementResult result = tx.run(q);
                while (result.hasNext()) {
                    Record record = result.next();
                    // Values can be extracted from a record by index or name.
//                    String r = record.get("name1label").asString();
                LOGGER.info("query run");
                    outputResult.Node1 = record.get("name1").toString();
                    outputResult.Node1label = record.get("name1label").toString();
                    outputResult.Relation = record.get("ril").toString();
                    outputResult.Node2 = record.get("name2").toString();
                    outputResult.Node2label = record.get("name2label").toString();


                }
//                LOGGER.info("output :{}", map);
                LOGGER.info("Out of string loop");
                System.out.println(outputResult);
                tx.success();  // Mark this write as successful.
                res.add(outputResult);
            }
        }
        return res;
    }

//    public List<OutputResult> runquery(Driver driver, String k1, String k2) {
//        LOGGER.info("received keywords from tagger");
//        Map<String, String> map = new HashMap<>();
//        List<OutputResult> res = new ArrayList();
//        String q;
//        // Sessions are lightweight and disposable connection wrappers.
//        try (Session session = driver.session()) {
//            // Wrapping Cypher in an explicit transaction provides atomicity
//            // and makes handling errors much easier.
//            if(k2.equals("MedicalCondition")){
//             q = "MATCH (A:" + k2 + "),p=(B)-[r]-(A) WHERE A.name contains '" + k1 + "' RETURN A.name As name1, labels(A) as name1label, TYPE(r) as ril, B.name As name2, labels(B) as name2label LIMIT 10";
//            }
//            else{
//                q="MATCH (A:" + k2 + "),p=(B)-[r]-(A),j=(C:Document)-[s]-(B),k=(D:Content)-[t]-(B) WHERE A.name contains '" + k1 + "' UNWIND D.paraId As paraid UNWIND C.id as docids RETURN A.name As name, labels(A) as label, TYPE(r) as rel, B.name As diseasename, labels(B) as diseaselabel, TYPE(s) AS diseaserel, C.name As docname, labels(C) As doclabel,D.name As contentname,D.id as docid, TYPE(t) As contentrel,paraid,docids LIMIT 10";
//            }
//            try (Transaction tx = session.beginTransaction()) {
//                OutputResult outputResult = new OutputResult();
//                LOGGER.info(q);
//                StatementResult result = tx.run(q);
//                while (result.hasNext()) {
//                    Record record = result.next();
//                LOGGER.info("query run");
//                outputResult.setDiseaseName(record.get("diseasename").toString());
//                if(k2.equals("AnatomicalStructure"))
//                    outputResult.setAnatomy(new ArrayList<String>()[]);
//                outputResult.setAnatomy();
//                    outputResult.Node1 = record.get("name1").toString();
//                    outputResult.Node1label = record.get("name1label").toString();
//                    outputResult.Relation = record.get("ril").toString();
//                    outputResult.Node2 = record.get("name2").toString();
//                    outputResult.Node2label = record.get("name2label").toString();
//                    Integer paraid=Integer.parseInt(record.get("paraid").toString());
//                    Integer docids=Integer.parseInt(record.get("docids").toString());
//                    Integer docid=Integer.parseInt(record.get("docid").toString());
//                    outputResult.doc=getdoc(docid);
//                    outputResult.para=getpara(paraid);
//                }
////                LOGGER.info("output :{}", map);
//                LOGGER.info("Out of string loop");
//                System.out.println(outputResult);
//                tx.success();  // Mark this write as successful.
//                res.add(outputResult);
//            }
//        }
//        return res;
//    }
    public String getpara(Integer paraId){
        Optional<ParaContent> savedList;
        savedList = paraContentService.getParaById(paraId);
        Integer id=savedList.get().getDocId();
        Optional<ExtractedFileData> doc = extractedDataService.getDocById(id);
        String content=doc.get().getContent();
        System.out.println("Para: "+savedList.get().getData());
        System.out.println("Contnet: "+content);
        return savedList.get().getData();
    }
    public String getdoc(Integer id){
        Optional<ExtractedFileData> doc=extractedDataService.getDocById(id);
        String content=doc.get().getContent();
        System.out.println("Contnet: "+content);
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
