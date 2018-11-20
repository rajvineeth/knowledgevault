
package com.stackroute.knowledgevault.documentidentifier.config;

import com.stackroute.knowledgevault.domain.JsonLDObject;
import com.stackroute.knowledgevault.domain.OutputForDoc;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Bean
    public ProducerFactory<String,Object> producerFactory(){
        Map<String,Object> configs = new HashMap<>();
        configs.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(configs);
    }

    @Bean
    public KafkaTemplate<String,Object> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public ProducerFactory<String, JsonLDObject> producerfactory1(){
        Map<String, Object> config=new HashMap<>();
        config.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return  new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, JsonLDObject> kafkaTemplate1(){
        return new KafkaTemplate<>(producerfactory1());
    }
}
