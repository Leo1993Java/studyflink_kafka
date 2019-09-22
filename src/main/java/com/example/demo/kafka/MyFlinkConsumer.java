package com.example.demo.kafka;

import com.example.demo.javaBean.PoBean;
import com.example.demo.javaBean.SourceBean;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer09;
import org.apache.flink.streaming.util.serialization.SerializationSchema;

import java.util.Properties;

public class MyFlinkConsumer {

//    public static void main(String[] args) {
//        long l1 = 0;
//        while (true){
//            try {
//                if(100 / (l1 -100) == 1){
//                    System.out.println("我报错了");
//                }else{
//                    System.out.println(l1);
//                }
//                l1++;
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//    }

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.enableCheckpointing(5000); // 非常关键，一定要设置启动检查点！！
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "120.78.72.119:9092");
//        properties.setProperty("zookeeper.connect", "192.168.83.112:2181");
        properties.setProperty("group.id", "test112");

        FlinkKafkaConsumer09<PoBean> flinkKafkaConsumer09 = new FlinkKafkaConsumer09<PoBean>("test",new SourceBean(),properties);
//        FlinkKafkaConsumer10<PoBean> flinkKafkaConsumer09 = new FlinkKafkaConsumer10<PoBean>("test",new SourceBean(),properties);

        DataStream<PoBean> dataStream = env.addSource(flinkKafkaConsumer09).rebalance();

        dataStream.filter(new FilterFunction<PoBean>() {
            @Override
            public boolean filter(PoBean poBean) throws Exception {
                return true;
            }
        }).writeToSocket("", 123, new SerializationSchema<PoBean>() {
            @Override
            public byte[] serialize(PoBean poBean) {
                return new byte[0];
            }
        });

        env.execute("to start my think");
    }
}
