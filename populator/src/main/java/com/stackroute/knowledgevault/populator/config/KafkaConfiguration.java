package com.stackroute.knowledgevault.populator.config;


import com.stackroute.knowledgevault.domain.JsonLDObject;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Configuration
@EnableKafka
public class KafkaConfiguration {

    @Bean
    public ConsumerFactory<String, String> consumerFactory(){

        Map<String, Object> config= new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"172.23.239.70:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG,"group_id");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(config);
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory factory=new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
    @Bean
    public ConsumerFactory<String, JsonLDObject> userConsumerFactory(){

        Map<String, Object> config=new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"172.23.239.70:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG,"group_json");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,JsonDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(config,new StringDeserializer(),new JsonDeserializer<>(JsonLDObject.class));
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory userKafkaListenerFactory(){
        ConcurrentKafkaListenerContainerFactory factory=new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(userConsumerFactory());
        return factory;

    }

//    @Bean
//    public ProducerFactory<String, Collection<Map>> producerfactory(){
//        Map<String, Object> config=new HashMap<>();
//        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.163.3.112:9092");
//        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//
//        return  new DefaultKafkaProducerFactory<>(config);
//    }
//
//    @Bean
//    public KafkaTemplate<String, Collection<Map>> kafkaTemplate(){
//        return new KafkaTemplate<>(producerfactory());
//    }
}
