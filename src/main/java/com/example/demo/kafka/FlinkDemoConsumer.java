package com.example.demo.kafka;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class FlinkDemoConsumer {

    public static void main(String[] args) {
        getKafkaData();
    }

    private static void getKafkaData() {
        String topic = "test";
        Properties kafkaProps = new Properties();
        kafkaProps.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaProps.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaProps.put("bootstrap.servers", "120.78.72.119:9092");
        kafkaProps.put("zookeeper.connect", "120.78.72.119:2181");
        kafkaProps.put("group.id", "farmtest1");
        kafkaProps.put("auto.offset.reset", "smallest");
        kafkaProps.put("enable.auto.commit", "true");
        ConsumerConnector consumer = Consumer.createJavaConsumerConnector(new ConsumerConfig(kafkaProps));

        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topic, 1); // 一次从主题中获取一个数据
        Map<String, List<KafkaStream<byte[], byte[]>>> messageStreams = consumer.createMessageStreams(topicCountMap);
        KafkaStream<byte[], byte[]> stream = messageStreams.get(topic).get(0);// 获取每次接收到的这个数据
        ConsumerIterator<byte[], byte[]> iterator = stream.iterator();
        while (iterator.hasNext()) {
            String message = new String(iterator.next().message());
            consumer.commitOffsets();
            System.out.println(message);
        }
    }

}
