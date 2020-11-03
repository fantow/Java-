package com.fantow.多线程;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableTest2 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // 1.测试completedFuture方法  --> 相当于是为CompletableFuture赋值
//        CompletableFuture<String> future1 = CompletableFuture.completedFuture("abc");
//        String str = future1.getNow("123");
//        System.out.println(str);


        // 2.测试runAsync()  --> 相当于使用ForkJoinPool线程去执行
//        CompletableFuture.runAsync(new Runnable() {
//            @Override
//            public void run() {
//                // 线程开始执行... ForkJoinPool.commonPool-worker-1
//                System.out.println("线程开始执行... " + Thread.currentThread().getName());
//
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                System.out.println("线程结束...");
//            }
//        });
//
//        // 为了保证CompletableFuture不会被提前结束(因为是守护线程)，主线程sleep更久
//        Thread.sleep(3000);

        // 3.测试thenApply()方法  --> thenApply()相当于同步执行
//        CompletableFuture<String> future3 = CompletableFuture.completedFuture("abc").thenApply((s)->{
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return s.toUpperCase();
//        });
//
//        // 会在这次就拿到结果
//        System.out.println(future3.get());
//        // 为了保证守护线程不被中断
//        Thread.sleep(4000);
//        System.out.println(future3.get());

        // 4.测试thenApplyAsync()方法  --> 相当于使用ExecutorService.sumbit() 返回后的future.get()， 会阻塞直到接收到结果
//        CompletableFuture<String> future4 = CompletableFuture.completedFuture("abc").thenApplyAsync((s) -> {
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return s.toUpperCase();
//        });
//
//        // 尝试获取  --> 这一步还是会阻塞，直到返回结果
//        System.out.println("尝试获取结果: " + future4.get());
//        Thread.sleep(4000);
//        System.out.println(future4.get());


        // 5.测试thenAccept()方法

        // 这个thenAccept()参数需要提供一个Consumer
//        CompletableFuture.completedFuture("abc").thenAccept((s)-> System.out.println(s));

        // 所以可以这样写一个回调函数：
//        CompletableFuture.completedFuture("abc").thenApplyAsync((s)->{
//            try {
//                Thread.sleep(1000);
//                return s.toUpperCase();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }).thenAccept((s)-> System.out.println(s));
//
//        Thread.sleep(2000);


        // 6.测试thenAcceptAsync()
//        CompletableFuture.completedFuture("abc").thenApplyAsync((s)->{
//            try {
//                Thread.sleep(2000);
//                return s.toUpperCase();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }).thenAcceptAsync((s)->{
//            System.out.println(s);
//        });
//
//        Thread.sleep(4000);

        // 7.测试runAfterBoth
        CompletableFuture.completedFuture("abc").thenApplyAsync((s)->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第一个completableFuture打印...");
            return s.toUpperCase();
        }).runAfterBoth(CompletableFuture.completedFuture("abc11").thenAccept((s)-> System.out.println("第二个completableFuture打印: " + s)),()->{
            System.out.println("runnable线程打印...");
        });

        Thread.sleep(4000);


    }
}
