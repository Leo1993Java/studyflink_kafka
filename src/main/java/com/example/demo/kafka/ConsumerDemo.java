package com.example.demo.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Properties;

public class ConsumerDemo {

    private static Logger log = LoggerFactory.getLogger(ConsumerDemo.class);

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "120.78.72.119:9092");
        /*props.put("zookeeper.connect", "120.78.72.119:2181");*/
        props.put("group.id", "CountryCoun222te1232r");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        //1.创建消费者
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);

        //2.订阅Topic
        //创建一个只包含单个元素的列表，Topic的名字叫作customerCountries
        consumer.subscribe(Collections.singletonList("test"));

        try {
            while (true) {//消费者是一个长期运行的程序，通过持续轮询向Kafka请求数据。在其他线程中调用consumer.wakeup()可以退出循环
                //在100ms内等待Kafka的broker返回数据.超市参数指定poll在多久之后可以返回，不管有没有可用的数据都要返回
                ConsumerRecords<String, String> records = consumer.poll(100);
                if(records.count()>0){
                    for (ConsumerRecord<String, String> record : records) {
                        log.info("消费到数据了 ==>" + record.topic() + record.partition() + record.offset() + record.key() + record.value());
                    }
                }
            }
        } finally {
            //退出应用程序前使用close方法关闭消费者，网络连接和socket也会随之关闭，并立即触发一次再均衡
            consumer.close();
        }
    }

}
