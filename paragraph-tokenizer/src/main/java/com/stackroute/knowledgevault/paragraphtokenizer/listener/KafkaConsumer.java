package com.stackroute.knowledgevault.paragraphtokenizer.listener;

import com.stackroute.knowledgevault.domain.Document;
import com.stackroute.knowledgevault.domain.ExtractedFileData;
import com.stackroute.knowledgevault.paragraphtokenizer.resource.DocResource;
import com.stackroute.knowledgevault.paragraphtokenizer.service.ParaTokenizerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaConsumer {

    @Autowired
    ParaTokenizerImpl paraTokenizer;

    @Autowired
    DocResource docResource;

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);
    List list;

    @KafkaListener(topics="extracted",groupId = "group_json", containerFactory= "documentKafkaListenerFactory")
    public void consumejson(ExtractedFileData extractedFileData){
       Document document = new Document(extractedFileData.getId(), extractedFileData.getContent());
        list = paraTokenizer.tokenizePara(document);
        LOGGER.info("list of documents: {}",list.toString());
        LOGGER.info("i'm in consumer");
        docResource.post(list);
    }

    public List getList() {
        return list;
    }
}
