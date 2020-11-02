package com.fantow.多线程;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleTest {
    public static void main(String[] args) {

//          service = Executors.newScheduledThreadPool(1);

        Timer timer = new Timer();


//        service.schedule(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        },10L, TimeUnit.MILLISECONDS);


        Date date = new Date(System.currentTimeMillis());

//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
        //
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("线程开始执行..." + new Date());
//                try {
//                    Thread.sleep(6000);
//                    System.out.println("线程执行结束:" + new Date());
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        },0,1000);

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("开始:" + new Date());
                try {
                    Thread.sleep(5000);
                    System.out.println("结束: " + new Date());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },0,1000);


//        service.schedule(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        },0,);




    }
}
