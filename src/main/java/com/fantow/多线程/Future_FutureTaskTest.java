package com.fantow.多线程;

import java.util.concurrent.*;

public class Future_FutureTaskTest {
    public static void main(String[] args) {

        FutureTask<Integer> futureTask = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return null;
            }
        });


        ExecutorService service = Executors.newFixedThreadPool(10);
        Future<?> future = service.submit(futureTask);


    }
}
