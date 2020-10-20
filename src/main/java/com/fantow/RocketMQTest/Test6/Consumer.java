package com.fantow.RocketMQTest.Test6;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class Consumer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("c1");
        consumer.setNamesrvAddr("192.168.0.100:9876");
        consumer.subscribe("topic11","*");

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for(MessageExt message : msgs){
                    System.out.println(" 线程信息:" + Thread.currentThread().getName() + " 消费到消息: " + new String(message.getBody()) + " -- " + message.getQueueId());
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

//        consumer.registerMessageListener(new MessageListenerOrderly() {
//            @Override
//            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
//                for(MessageExt message : msgs){
//                    System.out.println("消费到消息: " + new String(message.getBody()) + " -- " + message.getQueueId());
//                }
//                return ConsumeOrderlyStatus.SUCCESS;
//            }
//        });

        consumer.start();
    }
}
