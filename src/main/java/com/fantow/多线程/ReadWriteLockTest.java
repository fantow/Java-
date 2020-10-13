package com.fantow.多线程;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {
    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();

        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

        for(int i = 0;i < 2;i++){
//            writeLock.lock();
            lock.lock();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("写操作...");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
//            writeLock.unlock();
            lock.unlock();
        }


        for(int i = 0;i < 10;i++){
//            readLock.lock();
            lock.lock();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("读操作...");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
//            readLock.unlock();
            lock.unlock();
        }

    }
}
