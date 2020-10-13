package com.fantow.多线程;

import java.util.concurrent.Exchanger;

public class ExchangerTest {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        new Thread(new Runnable() {
            String str;
            @Override
            public void run() {
                try {
                    str = exchanger.exchange("b");
                    System.out.println(Thread.currentThread().getName() + " : " + str);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }).start();


//        new Thread(new Runnable() {
//            String str2;
//            @Override
//            public void run() {
//                try {
//                    str2 = exchanger.exchange(str2);
//                    System.out.println(Thread.currentThread().getName() + " : " + str2);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();


    }
}
