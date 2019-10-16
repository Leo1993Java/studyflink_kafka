package com.example.demo.Mongo;

import java.util.ArrayList;

public class MongoCluster {

    public static void main(String[] args) {
        MongoClient mongoClient = null;
        try{
            //连接mongo 集群
            List<ServerAddress> serverAddressList = new ArrayList<>();
            serverAddressList.add(new ServerAddress("ip1",29017));
            serverAddressList.add(new ServerAddress("ip2",29017));
            serverAddressList.add(new ServerAddress("ip3",29017));

            MongoCredential credential = MongoCredential.createCredential("myuser","mydb","mypasswd".toCharArray());
            //连接池 设置 注意readPreference 参数
            MongoClientOptions options = MongoClientOptions.builder().writeConcern(WriteConcern.NORMAL)
                    .ssl
                    ......

        }
    }


}
