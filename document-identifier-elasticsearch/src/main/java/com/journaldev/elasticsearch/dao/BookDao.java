package com.journaldev.elasticsearch.dao;

import au.com.bytecode.opencsv.CSVReader;
import com.journaldev.elasticsearch.bean.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeRequest;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeRequestBuilder;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.ElasticsearchClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.util.Collections.emptySet;

@Repository
public class BookDao {

    private final String INDEX = "bookdata";
    private final String TYPE = "books";

    private RestHighLevelClient restHighLevelClient;

    private ObjectMapper objectMapper;

    public BookDao( ObjectMapper objectMapper, RestHighLevelClient restHighLevelClient) throws IOException {
        this.objectMapper = objectMapper;
        this.restHighLevelClient = restHighLevelClient;
    }

    public Book insertBook(Book book){
       // book.setId(UUID.randomUUID().toString());
        Map<String, Object> dataMap = objectMapper.convertValue(book, Map.class);
        IndexRequest indexRequest = new IndexRequest(INDEX, TYPE, book.getId())
                .source(dataMap);
        try {
            IndexResponse response = restHighLevelClient.index(indexRequest);
        } catch(ElasticsearchException e) {
            e.getDetailedMessage();
        } catch (java.io.IOException ex){
            ex.getLocalizedMessage();
        }
        return book;
    }

    public Map<String, Object> getBookById(String id){
        GetRequest getRequest = new GetRequest(INDEX, TYPE, id);
        GetResponse getResponse = null;
        try {
            getResponse = restHighLevelClient.get(getRequest);
        } catch (java.io.IOException e){
            e.getLocalizedMessage();
        }
        Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();
        return sourceAsMap;
    }
//    public String analyze(String content){
//
//        AnalyzeRequest analyzeRequest=new AnalyzeRequest();
//        AnalyzeResponse analyzeResponse=null;
//        analyzeRequest.text(content);
//        analyzeRequest.tokenizer("standard");
//        analyzeRequest.addTokenFilter("lowercase");
//        analyzeRequest.analyzer("english");
//        analyzeResponse= restHighLevelClient.indices().analyze(analyzeRequest);
//
//    }


    public Map<String, Object> updateBookById(String id, Book book){
        UpdateRequest updateRequest = new UpdateRequest(INDEX, TYPE, id)
                .fetchSource(true);    // Fetch Object after its update
        Map<String, Object> error = new HashMap<>();
        error.put("Error", "Unable to update book");
        try {
            String bookJson = objectMapper.writeValueAsString(book);
            updateRequest.doc(bookJson, XContentType.JSON);
            UpdateResponse updateResponse = restHighLevelClient.update(updateRequest);
            Map<String, Object> sourceAsMap = updateResponse.getGetResult().sourceAsMap();
            return sourceAsMap;
        }catch (JsonProcessingException e){
            e.getMessage();
        } catch (java.io.IOException e){
            e.getLocalizedMessage();
        }
        return error;
    }

    public void deleteBookById(String id) {
        DeleteRequest deleteRequest = new DeleteRequest(INDEX, TYPE, id);
        try {
            DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest);
        } catch (java.io.IOException e){
            e.getLocalizedMessage();
        }
    }

    public float getScore(String content,String id){
        SearchRequest searchRequest=new SearchRequest(INDEX);
        searchRequest.types(TYPE);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        BoolQueryBuilder boolQuery = new BoolQueryBuilder();
        HashMap<String,String> fields=new HashMap();
        fields.put("content",content);
        fields.put("id",id);
        for (Map.Entry<String, String> entry : fields.entrySet()){
            boolQuery.must(QueryBuilders.matchQuery(entry.getKey(), entry.getValue()));
        }
        sourceBuilder.query(boolQuery);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        sourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse=null;
        float score=0;
        try{
            searchResponse=restHighLevelClient.search(searchRequest);
        }
        catch (java.io.IOException e){
            e.getLocalizedMessage();
        }
        SearchHit[] hits = searchResponse.getHits().getHits();
        for (SearchHit hit : hits) {
           score= hit.getScore();
        }
        return score;

    }


    public Collection<Map<String, Object>> search(String content){

        SearchRequest searchRequest=new SearchRequest(INDEX);
        searchRequest.types(TYPE);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchQuery("content", content));
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        sourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse=null;
        try{
            searchResponse=restHighLevelClient.search(searchRequest);
        }
        catch (java.io.IOException e){
            e.getLocalizedMessage();
        }
        SearchHit[] hits = searchResponse.getHits().getHits();
        Collection<Map<String, Object>> contracts = new LinkedList<>();
        for (SearchHit hit : hits) {
            System.out.println("hit: "+hit.getScore());
            contracts.add(hit.getSourceAsMap());
        }
        return contracts;

    }
    public Collection<Map<String, Object>> getAll(){

        SearchRequest searchRequest=new SearchRequest(INDEX);
        searchRequest.types(TYPE);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchAllQuery());
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        sourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));
        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse=null;
        try{
            searchResponse=restHighLevelClient.search(searchRequest);
        }
        catch (java.io.IOException e){
            e.getLocalizedMessage();
        }
        SearchHit[] hits = searchResponse.getHits().getHits();
        Collection<Map<String, Object>> contracts = new LinkedList<>();
        for (SearchHit hit : hits) {
            contracts.add(hit.getSourceAsMap());
        }
        return contracts;
    }
    public List<String> getDiseases() throws IOException {
        CSVReader reader = new CSVReader(new FileReader("document-identifier-elasticsearch/src/main/Docs/DOID.csv"),',');
        List<String[]> allRows = reader.readAll();
        List<String> diseases=new ArrayList<>();
        for(String[] row : allRows){
            diseases.add(row[1]);
        }
        return diseases;
    }

    public List<String> getSymptoms() throws IOException {
        CSVReader reader = new CSVReader(new FileReader("document-identifier-elasticsearch/src/main/Docs/SYMP.csv"),',');
        List<String[]> allRows = reader.readAll();
        List<String> diseases=new ArrayList<>();
        for(String[] row : allRows){
            diseases.add(row[1]);
        }
        return diseases;
    }

}
