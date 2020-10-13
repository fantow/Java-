package com.fantow.手写代码系列;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueue3<T> {
    private LinkedList<T> lists = new LinkedList<>();
    private volatile int size;
    private int capacity;

    private Lock lock = new ReentrantLock();
    private Condition Producer = lock.newCondition();
    private Condition Consumer = lock.newCondition();

    private BlockingQueue3(int capacity){
        this.capacity = capacity;
    }

    public void put(T t){
        try{
            lock.lock();
            try {
                while(size >= capacity){
                    System.out.println("队列已满，等待消费者");
                    Producer.await();
                }
            }catch (InterruptedException ex){
                // 当前线程异常，存入失败，唤醒另一个生产者线程
                Producer.notify();
                ex.printStackTrace();
            }
            size++;
            lists.add(t);
            Consumer.signal();
        }finally {
            lock.unlock();
        }
    }

    public T take(){
        try{
            lock.lock();
            try{
                while(size <= 0){
                    System.out.println("队列为空,等待生产者");
                    Consumer.await();
                }
            }catch (InterruptedException ex){
                Consumer.notify();
                ex.printStackTrace();
            }
            size--;
            T result = lists.removeFirst();
            Producer.signal();
            return result;
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        BlockingQueue3<Integer> blockingQueue3 = new BlockingQueue3<>(10);

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0;i < 20;i++){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("存入: " + i);
                    blockingQueue3.put(i);
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
                    System.out.println("获取:" + blockingQueue3.take());
                }
            }
        }).start();


    }

}
