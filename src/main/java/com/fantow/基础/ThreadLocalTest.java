package com.fantow.基础;

public class ThreadLocalTest {
    public static void main(String[] args) {
//        ThreadLocal<Integer> threadLocal1 = new ThreadLocal<>();
//        threadLocal1.set(10);
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Integer integer = threadLocal1.get();
//                System.out.println("其他线程获取到: " + integer);
//            }
//        }).start();
//
//        System.out.println("本线程获取到: " + threadLocal1.get());

        ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
            @Override
            protected Integer initialValue() {
                return 111;
            }
        };

        Integer integer = threadLocal.get();
        System.out.println(integer);

    }
}
