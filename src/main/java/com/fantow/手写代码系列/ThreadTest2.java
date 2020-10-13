package com.fantow.手写代码系列;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 写一个固定容量的同步容器，拥有put和get方法，以及getCount方法
// 能够支持2个生产者线程以及10个消费者线程的阻塞调用
public class ThreadTest2 {
    public static void main(String[] args) {
        BlockingQueue queue = new BlockingQueue(10);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0;i < 20;i++){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    queue.put(i);
                    System.out.println("存入: " + i);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0;i < 20;i++){
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("拿出:" + queue.take());
                }
            }
        }).start();

    }
}


class BlockingQueue{

    private static final List<Integer> list = new LinkedList<>();
    private int capacity;
    private volatile int currentSize = 0;

    // 这里用semaphore或者Condition是最合适的
    Lock lock = new ReentrantLock(true);
    Condition isEmpty = lock.newCondition();
    Condition isFull = lock.newCondition();


    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public void put(Integer num){
        try {
            lock.lockInterruptibly();
            if(currentSize == capacity){
                isFull.await();
            }
            list.add(num);
            if(currentSize == 0){
                isEmpty.signal();
            }
            currentSize++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    public int take(){
        Integer integer = -1;

        try {
            lock.lockInterruptibly();

            if(currentSize == 0){
                try {
                    isEmpty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if(currentSize == capacity){
                isFull.signal();
            }
            integer = list.remove(0);

            currentSize--;

            return integer;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return -1;
    }

}
