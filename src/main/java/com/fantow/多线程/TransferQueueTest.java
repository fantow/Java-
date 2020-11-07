package com.fantow.多线程;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

public class TransferQueueTest {

    // 一般介绍TransferQueue都是说它是LinkedBlockingQueue + synchronousQueue的实现
    public static void main(String[] args) throws InterruptedException {
        TransferQueue<Integer> queue = new LinkedTransferQueue<Integer>();

        // 先在队列中放入两个对象
        // 这个offer方法，即使没有take方法，也不会被阻塞
//        queue.offer(1);
//        queue.offer(2);

        // 使用take消费
//        try {
//            Integer integer = queue.take();
//            System.out.println(integer);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    queue.transfer(3);
                    System.out.println("结束阻塞...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(1000);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Integer integer = queue.take();
                    System.out.println(Thread.currentThread().getName() + "获取到了结果：" + integer);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
