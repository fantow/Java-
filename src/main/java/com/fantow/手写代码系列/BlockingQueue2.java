package com.fantow.手写代码系列;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BlockingQueue2<T> {

    private final LinkedList<T> lists = new LinkedList<>();

    private final int MAX;
    private int size = 0;

    public BlockingQueue2(int capacity) {
        this.MAX = capacity;
    }

    public synchronized void put(T t){
        while(size == MAX){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        lists.add(t);
        size++;
        this.notifyAll();
    }

    public synchronized T get(){
        T t = null;
        while(size == 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t = lists.removeFirst();
        size--;
        this.notifyAll();
        return t;
    }

    public static void main(String[] args) {
        BlockingQueue2<Integer> blockingQueue = new BlockingQueue2<>(10);

        // 生产者线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0;i < 20;i++){
                    try {
                        Thread.sleep(100);
                        System.out.println("存入了: " + i);
                        blockingQueue.put(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        // 消费者线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0;i < 20;i++){
                    try {
                        Thread.sleep(200);
                        Integer integer = blockingQueue.get();
                        System.out.println("获取到:" + integer);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


    }
}
