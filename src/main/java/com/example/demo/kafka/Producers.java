package com.example.demo.kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.record.Record;
import org.apache.kafka.common.serialization.StringSerializer;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.*;

public class Producers {

    public static void pro(){
        Properties kafkaProps = new Properties();
        kafkaProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "120.78.72.119:9092");

        kafkaProps.put("key.serializer", "org.apache.kafka.common.StringSerializer");
        kafkaProps.put("value.serializer", "org.apache.kafka.common.StringSerializer");
        /* kafkaProps.put("partitioner.class","org.apache.kafka.clients.producer.internals.DefaultPartitioner");*/
        /*KafkaProducer producer = new KafkaProducer<String, String>(kafkaProps);*/

        Producer<String, String> producer = new KafkaProducer<String,String>(kafkaProps,new StringSerializer(),new StringSerializer());

        for(int i = 0; i < 100; i++){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = simpleDateFormat.format(new Date());
            String msg = "时间："+time +"==>生产数据值：" + UUID.randomUUID().toString();
//            String msg = "时间："+time +"==>生产数据值：" + "小祥！ hello world";
            ProducerRecord<String, String> record =
                    new ProducerRecord<>("test", "PrecisionProducts", msg);//Topic Key Value
            producer.send(record,new DemoProducerCallback());
        }
    }

    public static void main(String[] args) {
        new Timer().schedule(new TimerTask() {
            public void run() {
                pro();
            }
            }, 0,5000);// 设定指定的时间time,此处为5000毫秒
    }

    private static class DemoProducerCallback implements Callback {
        @Override
        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
            if (e != null) {//如果Kafka返回一个错误，onCompletion方法抛出一个non null异常。
                e.printStackTrace();//对异常进行一些处理，这里只是简单打印出来
            }
        }
    }
}




