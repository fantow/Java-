package com.fantow.RocketMQTest.Test6;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.selector.SelectMessageQueueByHash;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.ArrayList;
import java.util.List;

public class Producer1 {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("g1");
        producer.setNamesrvAddr("192.168.0.100:9876");

        producer.start();

        Message message1 = new Message("topic11","Hello1".getBytes());
        Message message2 = new Message("topic11","Hello2".getBytes());
        Message message3 = new Message("topic11","Hello3".getBytes());
        Message message4 = new Message("topic11","Hello4".getBytes());
        Message message5 = new Message("topic11","Hello5".getBytes());

        List<Message> list = new ArrayList<>();
        list.add(message1);
        list.add(message2);
        list.add(message3);
        list.add(message4);
        list.add(message5);

        for(Message message : list){
            producer.send(message,new SelectMessageQueueByHash(),message.getBody());
            System.out.println("发送结束..");
        }

    }
}
