package com.example.demo.flink;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Properties;


/*
 * 每隔2秒计算最近4秒的数据
 */
public class ReadFromKafka {
    private static Logger logger = LoggerFactory.getLogger(ReadFromKafka.class);

    public static void main(String[] args) throws Exception {
        //StreamExecutionEnvironment是一个任务的启动的入口
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //kafka消费相关数据
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaProperties.KAFAKA_BROKER_LIST);
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "flink_consumer");
       // DataStream<Po> stream = env.addSource(new FlinkKafkaConsumer09<Po>());
//        DataStream<String> stream = env.addSource(new FlinkKafkaConsumer010<String>(KafkaProperties.TOPIC, new SimpleStringSchema(), properties));
//        stream.map(new MapFunction<String, String>() {
//            @Override
//            public String map(String value) throws Exception {
//                logger.info("进行过滤筛选 ==> " + value);
//                return new Date().toString() + " : " + value;
//            }
//        });
        //不做打印处理
        //.print();
        env.execute();
    }
}
