package com.fantow.RocketMQTest.Test3;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.MixAll;

import java.io.IOException;

public class Consumer {
    public static void main(String[] args) throws IOException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumer_1");
        consumer.setNamesrvAddr("192.168.0.100:9876");

        String filterCode = MixAll.file2String("D:\\源码学习\\Java基础练习\\src\\main\\java\\com\\fantow\\RocketMQTest\\Test3\\MyMessageFilter.java");
        System.out.println(filterCode);
    }
}
