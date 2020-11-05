package com.fantow.多线程;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Sleep_Condition_waitTest {

    // 分别来判断Sleep，Condition，Wait这三个方法在调用前持锁是否会释放锁
    public static void main(String[] args) throws InterruptedException {

        Object obj = new Object();

        // 1.先持锁再Sleep   --> 不会释放锁
//        synchronized (obj){
//            System.out.println("get here...");
//            Thread.sleep(10000);
//        }
//
//        new Thread(
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        synchronized (obj){
//                            System.out.println("here...");
//                        }
//
//                    }
//                }
//        ).start();

        // 2.Condition
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println("进入到线程1...");
                    condition.await();
                    System.out.println("线程1被唤醒...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }

            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程2执行了....");
            }
        }).start();

        Thread.sleep(5000);

        lock.lock();
        condition.signal();
        System.out.println("唤醒线程1...");
        lock.unlock();


    }

}
