package com.fantow.手写代码系列;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

// 实现一个容器，提供两个方法，add和size
// 写两个线程，线程1添加10个元素到容器中，
// 线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
public class ThreadTest1 {

    static Box box = new Box();

    static List<Object> lists = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) {

//        Thread t1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for(int i = 0;i < 10;i++){
//                    box.add(new Object());
//                    try {
//                        Thread.sleep(100);
//                        System.out.println("放入元素: " + i);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//
//        Thread t2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while(!Thread.currentThread().isInterrupted()){
//                    int size = box.getSize();
//                    System.out.println("当前容器内元素个数: " + size);
//                    if(size == 5){
//                        System.out.println("容器内元素个数已到5，中断线程");
//                        Thread.currentThread().interrupt();
//                    }
//                }
//            }
//        });
//
//        t1.start();
//        t2.start();

        // 使用线程安全的容器，保证内存可见性
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for(int i = 0;i < 9;i++){
//                   lists.add(new Object());
//                    System.out.println("添加");
//                }
//            }
//        }).start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while(true){
//                   if(lists.size() == 5){
//                       System.out.println("中断...");
//                       break;
//                   }
//                }
//            }
//        }).start();

        Object obj = new Object();

        // 主动实现线程间的通信
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                synchronized (obj){
//                    for(int i = 0;i < 9;i++){
//                        lists.add(new Object());
//                        System.out.println("向容器中添加元素: " + i);
//                        if(lists.size() == 5){
//                            System.out.println("notify...");
//                            obj.notify();
//                            try {
//                                obj.wait();
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//                    }
//                }
//            }
//        }).start();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                while(!Thread.currentThread().isInterrupted()) {
//                    synchronized (obj) {
//                        if(lists.size() != 5) {
//                            try {
//                                obj.wait();
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                        System.out.println("当前容器内元素为:" + lists.size() + " 中断...");
//
//                        obj.notify();
//                        Thread.currentThread().interrupt();
//                    }
//                }
//            }
//        }).start();


        // wait和notify与for循环搭配时的小坑
        AtomicInteger integer = new AtomicInteger(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (obj) {
                        if (integer.get() == 4) {
                            System.out.println("notify...");
                            obj.notify();
                            try {
                                obj.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("本次循环:" + integer.get());
                        integer.getAndIncrement();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                if(integer.get() != 4){
                    synchronized (obj){
                        try {
                            obj.wait();
                            System.out.println("线程2被唤醒...");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                System.out.println("线程2结束...");
            }
        }).start();


    }
}

class Box{

    private int size = 0;
    List<Object> list = new ArrayList<>();

    public void add(Object obj) {
        synchronized (list) {
            list.add(obj);
            size++;
        }
    }

    public int getSize(){
        return size;
    }
}
