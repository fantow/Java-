package com.fantow.多线程;

import java.util.concurrent.*;

public class CompletableFutureTest {
    public static void main(String[] args) {

        // 注意CompletableFuture使用ForkJoinPool中的守护线程运行任务，这会导致如果主线程结束，守护线程会提前结束
        ExecutorService service = Executors.newFixedThreadPool(10);

        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },service);

        CompletableFuture<String> future = CompletableFuture.completedFuture("abc");


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(completableFuture.isDone());

    }

    public static int method4(int preResult){
        return preResult + 1;
    }

    public static int method1(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("method1执行");
        return 1;
    }

    public static int method2(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("method2执行");
        return 2;
    }

    public static int method3(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("method3执行");
        return 3;
    }

}
