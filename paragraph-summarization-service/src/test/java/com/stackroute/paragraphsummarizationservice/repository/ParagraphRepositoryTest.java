package com.stackroute.paragraphsummarizationservice.repository;

import com.stackroute.paragraphsummarizationservice.domain.Paragraph;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

//This is integrated test we need DB server
//Find out what are testRunner
@RunWith(SpringRunner.class)
@DataMongoTest
public class ParagraphRepositoryTest {
    @Autowired
    ParagraphRepository paragraphRepository;
    Paragraph paragraph;

    @Before
    public void setUp()
    {
        paragraph = new Paragraph();
        paragraph.setId(10);
        paragraph.setTitle("Rang De Basanti");
        paragraph.setComments("good paragraph");
        paragraph.setLanguage("Hindi");

    }
    @After
    public void tearDown() throws Exception {
        paragraphRepository.deleteAll();
    }
    @Test
    public void testSaveMovie(){
        paragraphRepository.insert(paragraph);
        Paragraph fetchParagraph = paragraphRepository.findById(paragraph.getId()).get();
        Assert.assertEquals(10, fetchParagraph.getId());
    }
    @Test
    public void testSaveMovieFailure(){
        Paragraph testParagraph = new Paragraph(101,"spiderman","English","4 star");
        paragraphRepository.insert(testParagraph);
        paragraphRepository.insert(paragraph);
        Paragraph fetchParagraph = paragraphRepository.findById(paragraph.getId()).get();
        Assert.assertNotSame(fetchParagraph, paragraph);
    }
    @Test
    public void testDeleteMovie(){
        paragraphRepository.insert(paragraph);
        paragraphRepository.delete(paragraph);
        boolean fetchMovie = paragraphRepository.existsById(paragraph.getId());
        Assert.assertEquals(false,fetchMovie);
    }
    @Test
    public void testDeleteMovieFailure(){
        Paragraph testParagraph1 = new Paragraph(1,"dev1","telugu1","comment1");
        paragraphRepository.insert(testParagraph1);
        Paragraph fetchParagraph = paragraphRepository.findById(testParagraph1.getId()).get();
        Assert.assertNotSame(testParagraph1, fetchParagraph);
    }
    @Test
    public void updateMovieTest() {
        paragraphRepository.insert(paragraph);
        Paragraph paragraph1 = paragraphRepository.findById(paragraph.getId()).get();
        paragraph1.setComments("Sending");
        paragraphRepository.save(paragraph1);
        Assert.assertEquals("Sending", paragraph1.getComments());
    }
    @Test
    public void updateMovieTestFailure() {
        paragraphRepository.insert(paragraph);
        Paragraph paragraph1 = paragraphRepository.findById(paragraph.getId()).get();
        paragraph1.setComments("Sending");
        paragraphRepository.save(paragraph1);
        Assert.assertNotSame(paragraph, paragraph1);
    }
    @Test
    public void testgetByMovieTitle(){
//        paragraphRepository.insert(paragraph);
//        Paragraph fetchParagraph = paragraphRepository.getMovieByTitle(paragraph.getTitle());
//        Assert.assertEquals(10, fetchParagraph.getId());
    }
    @Test
    public void testgetByMovieTitleFailure(){
//        Paragraph testParagraph1 = new Paragraph(1,"dev1","telugu1","comment1");
//        paragraphRepository.insert(testParagraph1);
//        Paragraph fetchParagraph = paragraphRepository.getMovieByTitle(testParagraph1.getTitle());
//        Assert.assertNotSame(testParagraph1, fetchParagraph);
    }
    @Test
    public void testGetAllMovie(){
        List<Paragraph> listold = paragraphRepository.findAll();
        Paragraph paragraph1 = new Paragraph(10,"Gita Govindam","Telugu","3 star");
        Paragraph paragraph2 = new Paragraph(9,"Bommarillu","Telugu","4 star");
        paragraphRepository.insert(paragraph1);
        paragraphRepository.insert(paragraph2);
        List<Paragraph> listUpdated = paragraphRepository.findAll();
        Assert.assertEquals(listold.size()+2,listUpdated.size());
    }

}
