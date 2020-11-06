package com.fantow.多线程;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();

        Condition condition = lock.newCondition();

        // 检测一下condition是否含有顺序问题  --> 含有顺序，这个可以看signal的源码，它是从condition
        // 的条件队列中拿Node，再存入AQS中尝试获锁，所以两个调用condition.await()存在谁先被唤醒的情况
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    System.out.println("Thread1被阻塞...");
                    condition.await();
                    System.out.println("Thread1被唤醒...");
                    lock.unlock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Thread1").start();


        Thread.sleep(1000);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    System.out.println("Thread2被阻塞...");
                    condition.await();
                    System.out.println("Thread2被唤醒...");
                    lock.unlock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Thread2").start();

        Thread.sleep(1000);
        // 看看这个signal是否一定唤醒的是Thread1
        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                System.out.println("Thread3尝试唤醒它们...");
                condition.signalAll();
                lock.unlock();
            }
        }).start();


        // 再检测一个问题：如果先signal再await，这个await是否会被直接唤醒？应该不行，signal原理是将条件队列的Node移动到
        // AQS中，后调用await，条件队列中没有这个Node
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread1调用了signal方法");
                lock.lock();
                condition.signal();
                lock.unlock();
            }
        },"Thread1").start();

        Thread.sleep(1000);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    condition.await();
                    lock.unlock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread2被唤醒了");
            }
        },"Thread2").start();


    }
}
