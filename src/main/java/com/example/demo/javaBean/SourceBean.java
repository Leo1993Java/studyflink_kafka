package com.example.demo.javaBean;

import org.apache.flink.api.common.typeinfo.TypeInformation;
//import org.apache.flink.streaming.util.serialization.KeyedDeserializationSchema;
import org.apache.flink.streaming.util.serialization.KeyedDeserializationSchema;
import org.apache.kafka.common.header.Headers;

import java.io.IOException;

public class SourceBean implements KeyedDeserializationSchema {


//    @Override
//    public PoBean deserialize(byte[] key, byte[] value, String topic, int partition, long offset, Headers headers) throws IOException {
//        return new PoBean(new String(key),value,topic,offset,partition,headers.toArray());
//    }

    @Override
    public Object deserialize(byte[] key, byte[] value, String topic, int partition, long offset, Headers headers, long timestamp) throws IOException {
        return new PoBean(new String(key),value,topic,offset,partition,headers.toArray(),timestamp);
    }

    @Override
    public boolean isEndOfStream(Object o) {
        return false;
    }

    @Override
    public TypeInformation getProducedType() {
        return null;
    }
}
