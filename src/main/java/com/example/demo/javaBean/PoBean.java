package com.example.demo.javaBean;

import com.example.demo.utils.DateUtil;
import org.apache.kafka.common.header.Header;

import java.io.Serializable;
import java.util.Arrays;

public class PoBean implements Serializable {

    private String key;
    private byte[] value;
    private String topic;
    private long offset;
    private int partition;
    private String prudutime;
    private Header[] headers;

    public PoBean(String key,byte[] value,String topic,long offset,int partition,Header[] headers,long timestamp){
        this.key = key;
        this.value = value;
        this.topic = topic;
        this.offset = offset;
        this.partition = partition;
        this.headers = headers;
        this.prudutime = DateUtil.timestampToString(timestamp);
    }

    public Header[] getHeaders() {
        return headers;
    }


    public void setHeaders(Header[] headers) {
        this.headers = headers;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public byte[] getValue() {
        return value;
    }

    public void setValue(byte[] value) {
        this.value = value;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public long getOffset() {
        return offset;
    }

    public int getPartition() {
        return partition;
    }

    public void setPartition(int partition) {
        this.partition = partition;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "PoBean{" +
                "key='" + key + '\'' +
                ", value=" + Arrays.toString(value) +
                ", topic='" + topic + '\'' +
                ", offset=" + offset +
                ", partition=" + partition +
                ", prudutime='" + prudutime + '\'' +
                ", headers=" + Arrays.toString(headers) +
                '}';
    }

    public String getPrudutime() {
        return prudutime;
    }

    public void setPrudutime(String prudutime) {
        this.prudutime = prudutime;
    }
}
