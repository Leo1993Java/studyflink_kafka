package com.example.demo.kafka;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer09;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer09;

import java.util.Date;
import java.util.Properties;

public class FlinkConsumer {

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.enableCheckpointing(5000); // 非常关键，一定要设置启动检查点！！
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "120.78.72.119:9092");
//        properties.setProperty("zookeeper.connect", "192.168.83.112:2181");
        properties.setProperty("group.id", "test112");

        FlinkKafkaConsumer09<String> myConsumer = new FlinkKafkaConsumer09<>("test", new SimpleStringSchema(), properties);
        myConsumer.setStartFromEarliest();
        System.out.println("执行输入");
        DataStream<String> stream = env.addSource(myConsumer);
        DataStream ds = stream.map(new MapFunction<String, Object>() {
            @Override
            public Object map(String s) throws Exception {
                return s + "==" + new Date().getTime();
            }
        });

        FlinkKafkaProducer09<String> flinkKafkaProducer09 = new FlinkKafkaProducer09<String>("120.78.72.119:9092","test",new SimpleStringSchema());
        ds.addSink(flinkKafkaProducer09);
        System.out.println("执行输出");

        env.execute();
    }

}
