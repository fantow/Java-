package com.fantow.多线程;

import java.io.IOException;

public class ThreadStateTest {
    // 尝试在执行的线程中，调用interrupt()方法，修改它的中断标记，从而抛出Interrupted异常
    public static void main(String[] args) {

        Runner runner = new Runner();
        Thread thread = new Thread(runner);

        // 开始执行这个线程
        thread.start();

        try {
            // 阻塞主线程
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("在主线程中中断这个线程");
        thread.interrupt();

        System.out.println("Test end ...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread.getState());
    }
}

class Runner implements Runnable{

    @Override
    public void run() {
        try {
            aMethod();
        } catch (InterruptedException e) {
            System.out.println("当前线程中断标志:" + Thread.currentThread().isInterrupted());
            e.printStackTrace();
        }
    }

    // 假设这个方法会抛出中断异常
    public void aMethod() throws InterruptedException {
        while(!Thread.currentThread().isInterrupted()){
            Thread.sleep(100);
            System.out.println("线程正常执行");
        }
        System.out.println("调用了抛出中断异常的方法");
        throw new InterruptedException();
    }
}