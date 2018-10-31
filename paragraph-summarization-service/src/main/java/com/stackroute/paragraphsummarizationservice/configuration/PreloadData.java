package com.stackroute.paragraphsummarizationservice.configuration;

import com.stackroute.paragraphsummarizationservice.domain.Paragraph;
import com.stackroute.paragraphsummarizationservice.repository.ParagraphRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

@Configuration
public class PreloadData implements ApplicationListener<ContextRefreshedEvent>, CommandLineRunner {
    private ParagraphRepository paragraphRepository;

    public PreloadData(ParagraphRepository paragraphRepository) {
        this.paragraphRepository = paragraphRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        paragraphRepository.save(new Paragraph(3,"venom","english","save"));
        paragraphRepository.save(new Paragraph(4,"stree","hindi","save"));
    }
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        paragraphRepository.save(new Paragraph(1,"la la","english","save"));
        paragraphRepository.save(new Paragraph(2,"devadas","telugu","save"));

    }
}
