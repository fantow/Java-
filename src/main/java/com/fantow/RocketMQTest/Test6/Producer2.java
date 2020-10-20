package com.fantow.RocketMQTest.Test6;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

public class Producer2 {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("g2");
        producer.setNamesrvAddr("192.168.0.100:9876");

        producer.start();

        Message message = new Message("topic11","Hello++".getBytes());
        producer.send(message);
    }
}
