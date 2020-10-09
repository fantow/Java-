package com.fantow.多线程;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

public class MultiThreadTest {
    public static void main(String[] args) {

//        CountDownLatch countDownLatch = new CountDownLatch(1);
////        ---- test1 ----
//        Thread t1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    countDownLatch.await();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("执行了...");
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("阻塞结束...");
//            }
//        });
//
//        Thread t2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                countDownLatch.countDown();
//                System.out.println("t2...");
//                try {
//                    t1.join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("t2执行完成...");
//            }
//        });
//
//        t1.start();
//        t2.start();

//        ---- test2 ----
//        是否可以从一个terminated状态的线程，通过调用start()，再变为runnable
        Thread t1 = new Thread(new Runnable() {
            int a = 0;
            @Override
            public void run() {
                while (a <= 100){
                    System.out.println(a);
                    a++;
                }
            }
        });

        t1.start();
        // 这里通过join，保证t1执行结束，再去调用一次start
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        System.out.println("执行结束");

        System.out.println("第二次start");
        // Exception in thread "main" java.lang.IllegalThreadStateException
        t1.start();
    }
}
