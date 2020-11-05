package com.fantow.多线程;

public class ThreadGroupTest {
    public static void main(String[] args) throws InterruptedException {

        ThreadGroup g1 = new ThreadGroup("g1");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " 执行..." + Thread.currentThread().getThreadGroup());
            }
        };

        Thread t1 = new Thread(g1,runnable);
        Thread t2 = new Thread(g1,runnable);
        Thread t3 = new Thread(g1,runnable);
        Thread t4 = new Thread(runnable);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        Thread.sleep(2000);
        g1.list();

        g1.interrupt();

        Thread.sleep(1000);
        System.out.println("-----");
        g1.list();
    }
}
