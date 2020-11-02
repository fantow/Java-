package com.fantow.多线程;

public class ThreadInterruptedTest {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("线程未被中断");
                }
                System.out.println("线程被中断");
            }
        });

        thread.start();

        Thread.sleep(1000);

        Thread.State state = thread.getState();


        System.out.println("中断前状态：" + state);
        thread.interrupt();

        Thread.sleep(1000);

        state = thread.getState();
        System.out.println("中断后状态：" + state);
    }

}
