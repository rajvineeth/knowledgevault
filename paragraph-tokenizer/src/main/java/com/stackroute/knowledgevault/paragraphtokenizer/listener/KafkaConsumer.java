package com.stackroute.knowledgevault.paragraphtokenizer.listener;

import com.stackroute.knowledgevault.domain.Document;
import com.stackroute.knowledgevault.domain.ExtractedFileData;
import com.stackroute.knowledgevault.paragraphtokenizer.resource.DocResource;
import com.stackroute.knowledgevault.paragraphtokenizer.service.ParaTokenizerImpl;
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

    List list;

    /*@KafkaListener(topics="consume3", groupId ="group_id")
    public void consume(String message){
        System.out.println("consumed message:"+message);
    }*/
    @KafkaListener(topics="consume4",groupId = "group_json", containerFactory= "documentKafkaListenerFactory")
    public void consumejson(ExtractedFileData extractedFileData){
       Document document = new Document(extractedFileData.getId(), extractedFileData.getContent());
        list = paraTokenizer.tokenizePara(document);
        System.out.println(list.toString());
        System.out.println("i'm in consumer");
       docResource.post(list);
        System.out.println("consumed message"+ extractedFileData.toString());
    }

    public List getList() {
        return list;
    }
}
