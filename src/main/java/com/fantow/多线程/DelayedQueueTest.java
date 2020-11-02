package com.fantow.多线程;

import sun.nio.ch.ThreadPool;

import java.util.concurrent.*;

public class DelayedQueueTest {

    public static void main(String[] args) {

//        ThreadPoolExecutor executor = new ThreadPoolExecutor(1,Integer.MAX_VALUE,0L,TimeUnit.MILLISECONDS, new DelayQueue<MyDelayed>());

    }

}

class MyDelayed implements Delayed{

    @Override
    public long getDelay(TimeUnit unit) {
        return 0;
    }

    @Override
    public int compareTo(Delayed o) {

        return 0;
    }
}
