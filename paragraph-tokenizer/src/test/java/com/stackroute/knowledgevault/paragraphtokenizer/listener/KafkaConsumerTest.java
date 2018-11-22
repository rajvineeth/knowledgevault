//package com.stackroute.knowledgevault.paragraphtokenizer.listener;
//
//import org.apache.kafka.common.serialization.*;
//import org.apache.kafka.streams.StreamsConfig;
//import org.apache.kafka.streams.Topology;
//import org.apache.kafka.streams.TopologyTestDriver;
//import org.apache.kafka.streams.state.KeyValueStore;
//import org.apache.kafka.streams.state.Stores;
//import org.apache.kafka.streams.test.ConsumerRecordFactory;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.Properties;
//
//import static org.junit.Assert.*;
//
//public class KafkaConsumerTest {
//    private TopologyTestDriver testDriver;
//    private KeyValueStore<String, Long> store;
//
//    private StringDeserializer stringDeserializer = new StringDeserializer();
//    private LongDeserializer longDeserializer = new LongDeserializer();
//    private ConsumerRecordFactory<String, Long> recordFactory =
//            new ConsumerRecordFactory<>(new StringSerializer(), new LongSerializer());
//
//    @Before
//    public void setUp() throws Exception {
//        Topology topology = new Topology();
//        topology.addSource("sourceProcessor", "input-topic");
//        topology.addProcessor("aggregator", new CustomMaxAggregatorSupplier(), "sourceProcessor");
//        topology.addStateStore(
//                Stores.keyValueStoreBuilder(
//                        Stores.inMemoryKeyValueStore("aggStore"),
//                        Serdes.String(),
//                        Serdes.Long()).withLoggingDisabled(),
//                        "aggregator");
//        topology.addSink("sinkProcessor", "result-topic", "aggregator");
//
//        //setup test driver
//        Properties config = new Properties();
//        config.setProperty(StreamsConfig.APPLICATION_ID_CONFIG, "maxAggregation");
//        config.setProperty(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "dummy:1234");
//        config.setProperty(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
//        config.setProperty(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.Long().getClass().getName());
//        testDriver = new TopologyTestDriver(topology, config);
//
//        //pre-populate store
//        store = testDriver.getKeyValueStore("aggStore");
//        store.put("a", 21L);
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        testDriver.close();
//    }
//
//    @Test
//
//
//    @Test
//    public void getList() {
//    }
//}