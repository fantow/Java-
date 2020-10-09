package com.fantow.ThreadLocal;

public class ThreadLocalTest {
    public static void main(String[] args) {
        ThreadLocal<Person> threadLocal = new ThreadLocal<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal.set(new Person());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal.get();
            }
        }).start();

        // ThreadLocal.get()执行流程
        // 1.在get方法中先获取到Thread.currentThread
        // 2.根据当前线程获取到ThreadLocal.ThreadLocalMap
        // 3.可以追踪源码发现，在ThreadLocal.createMap(Thread t,T firstValue)中看到
        // t.threadLocals = new ThreadLocalMap(this, firstValue);  这个this是一个ThreadLocal
        // firstValue 就是new Person
        // 为什么这样设计：因为一个Thread中可能不止存在一个ThreadLocal，所以使用了ThreadLocalMap
        // map中可以根据指定的ThreadLocal，找到对应的Value


    }
}

class Person{
    String name = "name";
}