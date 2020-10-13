package com.fantow.多线程;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {

    static Thread thread1 = new Thread();

    public static void main(String[] args) {

        class MyRunner implements Runnable{
            @Override
            public void run() {
                for(int i = 0;i < 10;i++){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(i);
                    // 在之前释放的unpark信号，再后面park时会使用到
                    if(i == 2){
                        System.out.println("unpark了一次");
                        LockSupport.unpark(thread1);
                    }
                    if(i == 5){
                        System.out.println("再unpark了一次");
                        LockSupport.unpark(thread1);
                    }
                    if(i == 7){
                        System.out.println("park了一次");
                        LockSupport.park();
                    }

                    if(i == 4){
                        System.out.println("再park了一次");
                        LockSupport.park();
                    }
                }
            }
        }

//        Thread thread2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });

        thread1 = new Thread(new MyRunner());

        thread1.start();
//        thread2.start();


        System.out.println(" --- end --- ");
    }
}
