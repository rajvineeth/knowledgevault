package com.stackroute.knowledgevault.knowledgevault.paragraphtokenizer.service;

import com.stackroute.knowledgevault.domain.ExtractedFileData;
import com.stackroute.knowledgevault.paragraphtokenizer.service.ParaTokenizer;
import com.stackroute.knowledgevault.paragraphtokenizer.service.ParaTokenizerImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ParaTokenizerImplTest {

    ParaTokenizer paraTokenizer;
    @Before
    public void setUp() throws Exception {
        paraTokenizer = new ParaTokenizerImpl();
    }

    @After
    public void tearDown() throws Exception {
        paraTokenizer = null;
    }

    @Test
    public void tokenizePara() {
        Integer id = 21;
        String text = "Alex Valero knew he could trust his instincts. Over the years, he had followed them to law school in Spain, then Afghanistan as a UN peacekeeper, then Zaragoza, where he was a successful graphic designer for a global sports brand. It was there he received the news that would change his life: his father had died from a sudden heart attack in Granada. An only child, Alex immediately crossed the country to be with his mother, and as the weeks passed, he decided to stay.\n" +
                "\n" +
                "Granada is a small city, and Alex soon discovered that jobs were hard to find, despite his qualifications. He was hardly alone. At that time in 2016, 4.2 million people across Spain were looking for work, with levels of youth unemployment over 40 percent. Alex searched for a job for almost a year without success, so when his girlfriend suggested that he come along to a digital marketing course at the local university, Alex jumped at the chance. Together, they completed the Google Actívate program, part of the Grow with Google initiative offering free online and face-to-face courses in digital skills. More than 335,000 people have trained with Google Actívate so far, as Google pursues its commitment to help 1 million Europeans find work or grow their businesses by 2020.\n" +
                "\n" +
                "As he worked through the course, Alex began recognizing a trend: many of his friends told familiar stories of fruitless job applications and the struggle to make ends meet. And while out cycling one day, Alex had a brainwave: bars and restaurants often need extra staff at short notice and struggle to find them in time. What if an app could connect his unemployed friends with restaurants that need help?";
        ExtractedFileData doc = new ExtractedFileData(id,"",text);

        List expList = new ArrayList();
        expList.add(new ExtractedFileData(id,"","Alex Valero knew he could trust his instincts. Over the years, he had followed them to law school in Spain, then Afghanistan as a UN peacekeeper, then Zaragoza, where he was a successful graphic designer for a global sports brand. It was there he received the news that would change his life: his father had died from a sudden heart attack in Granada. An only child, Alex immediately crossed the country to be with his mother, and as the weeks passed, he decided to stay."));
        expList.add(new ExtractedFileData(id,"","Granada is a small city, and Alex soon discovered that jobs were hard to find, despite his qualifications. He was hardly alone. At that time in 2016, 4.2 million people across Spain were looking for work, with levels of youth unemployment over 40 percent. Alex searched for a job for almost a year without success, so when his girlfriend suggested that he come along to a digital marketing course at the local university, Alex jumped at the chance. Together, they completed the Google Actívate program, part of the Grow with Google initiative offering free online and face-to-face courses in digital skills. More than 335,000 people have trained with Google Actívate so far, as Google pursues its commitment to help 1 million Europeans find work or grow their businesses by 2020."));
        expList.add(new ExtractedFileData(id,"","As he worked through the course, Alex began recognizing a trend: many of his friends told familiar stories of fruitless job applications and the struggle to make ends meet. And while out cycling one day, Alex had a brainwave: bars and restaurants often need extra staff at short notice and struggle to find them in time. What if an app could connect his unemployed friends with restaurants that need help?"));

        List list = paraTokenizer.tokenizePara(doc);

        assertEquals(expList,list);
    }
}