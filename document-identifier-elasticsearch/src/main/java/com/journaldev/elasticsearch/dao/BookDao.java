package com.journaldev.elasticsearch.dao;

import com.journaldev.elasticsearch.bean.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.ElasticsearchException;
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
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.TimeUnit;

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
        book.setId(UUID.randomUUID().toString());
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
    public float getScore(String content){
        SearchRequest searchRequest=new SearchRequest(INDEX);
        searchRequest.types(TYPE);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchQuery("content", content));
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
        Collection<Map<String, Object>> contracts = new LinkedList<>();
        for (SearchHit hit : hits) {
           score= hit.getScore();
            contracts.add(hit.getSourceAsMap());
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
            System.out.println(hit.getScore());
            contracts.add(hit.getSourceAsMap());
        }
        return contracts;

    }


}
