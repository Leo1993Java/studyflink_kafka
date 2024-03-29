//package com.example.demo.kafkaDemo;
//
//import com.example.demo.javaBean.PoBean;
//import org.apache.flink.api.common.functions.FilterFunction;
//import org.apache.flink.streaming.api.TimeCharacteristic;
//import org.apache.flink.streaming.api.datastream.DataStream;
//import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
//import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer09;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.ApplicationEvent;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Service;
//
//import java.util.Properties;
//
///**
// * KafkaMessageStreaming类（Flink入口类，封装了对于Kafka消息的处理逻辑。本例每10秒统计一次结果并写入到本地文件）
// */
//@Service
//@Configuration
//public class KafkaMessageStreaming implements ApplicationListener {
//
//    @Value("${flink.path}")
//    private String path;
//
//    public static void main(String[] args) throws Exception {
//        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//        env.enableCheckpointing(5000); // 非常关键，一定要设置启动检查点！！
//        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
//
//        Properties props = new Properties();
//        props.setProperty("bootstrap.servers", "120.78.72.119:9092");
//        props.setProperty("group.id", "flink-group");
//
//        FlinkKafkaConsumer09<PoBean> flinkKafkaConsumer09 =
//                new FlinkKafkaConsumer09<PoBean>("test",new Po(),props);
//
//       DataStream<PoBean> dataStream = env.addSource(flinkKafkaConsumer09);
//
//       dataStream.filter(new FilterFunction<PoBean>() {
//           @Override
//           public boolean filter(PoBean po) throws Exception {
//               return true;
//           }
//       });
//
////        FlinkKafkaConsumer010<String> consumer =
////                new FlinkKafkaConsumer010<>("test", new SimpleStringSchema(), props);
//
//       /* FlinkKafkaConsumer09<String> consumer =
//                new FlinkKafkaConsumer09<>("test", new SimpleStringSchema(), props);*/
////        consumer.assignTimestampsAndWatermarks(new MessageWaterEmitter());
////
////        DataStream<Tuple2<String, Long>> keyedStream = env
////                .addSource(consumer)
////                .flatMap(new MessageSplitter())
////                .keyBy(0)
////                .timeWindow(Time.seconds(10))
////
////                .apply(new WindowFunction<Tuple2<String, Long>, Tuple2<String, Long>, Tuple, TimeWindow>() {
////                    @Override
////                    public void apply(Tuple tuple, TimeWindow window, Iterable<Tuple2<String, Long>> input, Collector<Tuple2<String, Long>> out) throws Exception {
////                        long sum = 0L;
////                        int count = 0;
////                        for (Tuple2<String, Long> record: input) {
////                            sum += record.f1;
////                            count++;
////                        }
////                        Tuple2<String, Long> result = input.iterator().next();
////                        result.f1 = sum / count;
////                        out.collect(result);
////                    }
////                });
////        /*keyedStream.writeAsText(path);*/
//        //keyedStream.print();
//        env.execute("Flink-Kafka demo");
//    }
//
////    public static void main(String[] args) throws Exception {
//////
////    }
//
//    private Logger logger = LoggerFactory.getLogger(KafkaMessageStreaming.class);
//    @Override
//    public void onApplicationEvent(ApplicationEvent applicationEvent) {
//        logger.info("启动kafka-flink 流式消费");
//        try {
//           // doing();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
