package com.fantow.多线程;

import java.awt.*;

public class ThreadLocalTest2 {
    // 测试对于弱引用的ThreadLocal Key，如果GC，这个线程是否还能使用get访问到value?

    static ThreadLocal<Person1> threadLocal = new ThreadLocal<>();
    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Person1 person = new Person1();
                person.name = "name1";
                threadLocal.set(person);
                System.out.println(threadLocal.get());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("sleep结束...");
                System.out.println(threadLocal.get());
            }
        });

        thread1.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadLocal = null;
//        System.gc();
        System.out.println("GC结束");


    }
}

class Person1{
    String name = "person";
}