package com.fantow.RocketMQTest.Test2;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.ArrayList;
import java.util.List;

public class Producer {
    public static void main(String[] args) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("group1");
        producer.setNamesrvAddr("192.168.0.100:9876");
        producer.start();

        Message message1 = new Message("topic-1","tag-1","消息1".getBytes());
        Message message2 = new Message("topic-1","tag-1","消息2".getBytes());
        Message message3 = new Message("topic-1","tag-2","消息3".getBytes());

        List<Message> messages = new ArrayList<>();
        messages.add(message1);
        messages.add(message2);
        messages.add(message3);

        producer.send(messages);

        System.out.println("发送结束...");
    }
}
