package com.stackroute.loaders;

import com.stackroute.domain.Document;
import com.stackroute.dao.DocumentDao;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class Loaders implements ApplicationListener<ContextRefreshedEvent>  {
    private DocumentDao documentDao;
    public Loaders(DocumentDao documentDao1){this.documentDao = documentDao1;}
//    @KafkaListener(topics="kafka_example", groupId ="group_id")
//    public void consume(String message){
//        System.out.println("consumed message:"+message);
//    }
//    @KafkaListener(topics="kafka_example_jsonh",groupId = "group_json", containerFactory= "userKafkaListenerFactory")
//    public void consumejson(User USER){
//        System.out.println("consumed message"+USER.toString());
//    }
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {

            documentDao.insertDoc(new Document("10","title",new String(Files.readAllBytes(Paths.get("document-identifier-elasticsearch/src/main/Docs/document1.txt")))));
            documentDao.insertDoc(new Document("11","title",new String(Files.readAllBytes(Paths.get("document-identifier-elasticsearch/src/main/Docs/document2.txt")))));
            documentDao.insertDoc(new Document("12","title",new String(Files.readAllBytes(Paths.get("document-identifier-elasticsearch/src/main/Docs/document3.txt")))));
            documentDao.insertDoc(new Document("13","title",new String(Files.readAllBytes(Paths.get("document-identifier-elasticsearch/src/main/Docs/document4.txt")))));
            documentDao.insertDoc(new Document("14","title",new String(Files.readAllBytes(Paths.get("document-identifier-elasticsearch/src/main/Docs/document5.txt")))));
 } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
