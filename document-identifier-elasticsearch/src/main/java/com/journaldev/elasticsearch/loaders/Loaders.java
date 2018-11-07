package com.journaldev.elasticsearch.loaders;

import com.journaldev.elasticsearch.bean.Book;
import com.journaldev.elasticsearch.dao.BookDao;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class Loaders implements ApplicationListener<ContextRefreshedEvent>  {
    private BookDao bookDao;
    public Loaders(BookDao bookDao1){this.bookDao=bookDao1;}

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
              bookDao.insertBook(new Book("10","title",new String(Files.readAllBytes(Paths.get("document-identifier-elasticsearch/src/main/Docs/document1.txt")))));
            bookDao.insertBook(new Book("11","title",new String(Files.readAllBytes(Paths.get("document-identifier-elasticsearch/src/main/Docs/document2.txt")))));
            bookDao.insertBook(new Book("12","title",new String(Files.readAllBytes(Paths.get("document-identifier-elasticsearch/src/main/Docs/document3.txt")))));
            bookDao.insertBook(new Book("13","title",new String(Files.readAllBytes(Paths.get("document-identifier-elasticsearch/src/main/Docs/document4.txt")))));
            bookDao.insertBook(new Book("14","title",new String(Files.readAllBytes(Paths.get("document-identifier-elasticsearch/src/main/Docs/document5.txt")))));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
