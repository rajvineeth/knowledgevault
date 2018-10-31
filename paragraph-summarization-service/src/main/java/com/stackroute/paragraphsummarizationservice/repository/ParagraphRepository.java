package com.stackroute.paragraphsummarizationservice.repository;

import com.stackroute.paragraphsummarizationservice.domain.Paragraph;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ParagraphRepository extends MongoRepository<Paragraph,Integer> {
//    @Query("{ 'title' : ?0 }")
//    public Paragraph getMovieByTitle(@Param("title") String title);
}
