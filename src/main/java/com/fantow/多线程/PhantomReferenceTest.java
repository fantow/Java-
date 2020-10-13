package com.fantow.多线程;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

public class PhantomReferenceTest {

    private static final ReferenceQueue<Object> dummyQueue = new ReferenceQueue<>();

    public static void main(String[] args) {
        Object obj = new Object();
        Cleaner cleaner = new Cleaner(obj,dummyQueue);

        System.out.println(cleaner.state);
        System.out.println("队列情况:" + dummyQueue.poll());
        cleaner.state = 100;

        System.gc();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Cleaner poll = (Cleaner)dummyQueue.poll();
        System.out.println(poll);
        System.out.println("队列情况:" + cleaner.state);
        System.out.println(cleaner.state);

    }
}

class Cleaner extends PhantomReference<Object>{

    public int state = 0;

    public Cleaner(Object var1,ReferenceQueue dummyQueue){
        super(var1,dummyQueue);
    }

}
