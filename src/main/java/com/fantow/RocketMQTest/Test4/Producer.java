package com.fantow.RocketMQTest.Test4;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

public class Producer {
    public static void main(String[] args) throws MQClientException {
        TransactionMQProducer producer = new TransactionMQProducer("g1");

        producer.setTransactionListener(new TransactionListener() {
            @Override
            public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {

                // 执行事务逻辑
                // 比如获取订单数据
                // p
//                return LocalTransactionState.COMMIT_MESSAGE;
                return LocalTransactionState.ROLLBACK_MESSAGE;
            }

            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt msg) {



                return LocalTransactionState.COMMIT_MESSAGE;
            }
        });

        producer.start();

        producer.sendMessageInTransaction(new Message(),null);


    }
}
