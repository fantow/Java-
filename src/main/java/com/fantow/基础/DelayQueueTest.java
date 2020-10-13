package com.fantow.基础;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueTest {
    public static void main(String[] args) {
        DelayQueue<MyTask> queue = new DelayQueue<>();

        MyTask task1 = new MyTask("name1",1000L);
        MyTask task2 = new MyTask("name2",500L);
        MyTask task3 = new MyTask("name3",1500L);
        queue.offer(task1);
        queue.offer(task2);
        queue.offer(task3);

        while (!queue.isEmpty()){
            MyTask task = queue.poll();
            System.out.println(task.name + " : " + task.runningTime);
        }

    }
}

class MyTask implements Delayed{

    String name ;
    long runningTime;

    public MyTask(String name, long runningTime) {
        this.name = name;
        this.runningTime = runningTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(runningTime - System.currentTimeMillis(),TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if(this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)){
            return -1;
        }else if(this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)){
            return 1;
        }else{
            return 0;
        }
    }
}
