package com.stackroute.knowledgevault.webscraping.controller;

import com.stackroute.knowledgevault.domain.UrlClass;
import com.stackroute.knowledgevault.webscraping.services.Scores;
import com.stackroute.knowledgevault.webscraping.services.webScraping;
import com.stackroute.knowledgevault.domain.ScrapedData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;


/**
 * Controller class, to make GET request.
 */

@RestController
@RequestMapping("/api/v1/webscraper")
@CrossOrigin
public class webScrapingController {

    @Qualifier("webScrapingImpl")
    private webScraping webScraper;

    private String url;
    private static final Logger LOGGER = LoggerFactory.getLogger(webScrapingController.class);

    @Autowired
    public webScrapingController(webScraping webScraper) {
        this.webScraper = webScraper;
    }

    @Autowired
    private KafkaTemplate<String, ScrapedData> kafkaTemplate;

    private static final String KafkaTopic ="scraperOutput";

    /*
        Function to produce the processed documents to the Kafka topic "prod2" and as a get request.
        An object of DocumentService is called to process the docs present in the local database.
    */

    @PostMapping()
    public ResponseEntity<?> gettingUrl(@RequestBody UrlClass urlClass){
        this.url = urlClass.getUrl();
        LOGGER.info(url);
        return new ResponseEntity<>(url, HttpStatus.OK);
    }


    @GetMapping()
    public ResponseEntity<?> getAllTerms(){
        ResponseEntity responseEntity;
        webScraper.getURL(url);
        webScraper.getScoredDocs();
        ScrapedData pojoClass = new ScrapedData(url, webScraper.getEvaluatedTitle(),webScraper.getTitle(), webScraper.getDescription());
        kafkaTemplate.send(KafkaTopic, pojoClass);
        responseEntity = new ResponseEntity<>(pojoClass, HttpStatus.OK);
        return responseEntity;
    }

}