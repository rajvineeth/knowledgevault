package com.stackroute.knowledgevault.loaders;

import com.stackroute.knowledgevault.domain.ExtractedFileData;
import com.stackroute.knowledgevault.dao.DocumentDao;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class Loaders implements ApplicationListener<ContextRefreshedEvent>  {
    private DocumentDao documentDao;
    public Loaders(DocumentDao documentDao1){this.documentDao = documentDao1;}
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
            System.out.println("inside loader");
            documentDao.insertDoc(new ExtractedFileData(10,"title",new String(Files.readAllBytes(Paths.get("document-identifier-elasticsearch/src/main/Docs/document1.txt")))));
            documentDao.insertDoc(new ExtractedFileData(11,"title",new String(Files.readAllBytes(Paths.get("document-identifier-elasticsearch/src/main/Docs/document2.txt")))));
            documentDao.insertDoc(new ExtractedFileData(12,"title",new String(Files.readAllBytes(Paths.get("document-identifier-elasticsearch/src/main/Docs/document3.txt")))));
            documentDao.insertDoc(new ExtractedFileData(13,"title",new String(Files.readAllBytes(Paths.get("document-identifier-elasticsearch/src/main/Docs/document4.txt")))));
            documentDao.insertDoc(new ExtractedFileData(14,"title",new String(Files.readAllBytes(Paths.get("document-identifier-elasticsearch/src/main/Docs/document5.txt")))));
 } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
