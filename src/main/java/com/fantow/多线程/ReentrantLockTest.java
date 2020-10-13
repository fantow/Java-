package com.fantow.多线程;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    static ReentrantLock lock = new ReentrantLock(false);

    public static void main(String[] args) {

        Runner runner = new Runner();

        Thread t1 = new Thread(runner);
        Thread t2 = new Thread(runner);

        t1.start();
        t2.start();
    }

    static class Runner implements Runnable{
        @Override
        public void run() {
            for(int i = 0;i < 100;i++) {
                try {
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " 打印...");
                }finally {
                    lock.unlock();
                }
            }
        }
    }

}


