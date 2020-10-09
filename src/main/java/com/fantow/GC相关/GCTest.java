package com.fantow.GC相关;

import sun.awt.windows.ThemeReader;
import sun.misc.Unsafe;

import java.io.IOException;
import java.lang.ref.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class GCTest {
    public static void main(String[] args) throws IOException {

//        ---- 强引用 ----
        // 强引用,finalize() 测试
//        M m = new M();
//        m = null;
//
//        System.gc();
//        // 用阻塞主线程
//        System.in.read();

//        ---- 软引用 ----
//        会在内存不足时被回收
//        需要设置最大堆空间为20M -Xms20M
//        ReferenceQueue<byte[]> queue = new ReferenceQueue<>();
//
//        SoftReference<byte[]> softReference = new SoftReference<>(new byte[1024 * 1024 * 10],queue);
//        System.gc();
//
//        System.out.println("当前引用队列状态：" + queue.poll());
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        // 因为内存充足，所以不会被回收
//        // 这样做会使软引用变为强引用，导致GC不会回收这部分
//        System.out.println(softReference.get());
//
//        // 再申请10M
//        byte[] buffer = new byte[1024 * 1024 * 10];
//        System.gc();
//
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("发生GC后，队列中的状态:" + queue.poll());
//        System.out.println(softReference.get());
        // 被回收

//        ---- 弱引用 ----
//        WeakReference<byte[]> weakReference = new WeakReference<>(new byte[1024 * 1024 * 10]);
//        System.out.println(weakReference.get());
//
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.gc();
//        System.out.println(weakReference.get());


//        ---- 虚引用 ----
//        ReferenceQueue<byte[]> referenceQueue = new ReferenceQueue<>();
//        PhantomReference<byte[]> phantomReference = new PhantomReference<>(new byte[1024 * 1024 * 10],referenceQueue);
//
//        // 无法通过虚引用对象的get方法，获得这个对象.  返回null
//        System.out.println(phantomReference.get());


//        ---- 虚引用的作用 ----
        List<Object> list = new ArrayList<>();
        ReferenceQueue<M> queue = new ReferenceQueue<>();
        PhantomReference<M> phantomReference = new PhantomReference<>(new M(),queue);

        System.out.println(phantomReference.get());

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    System.out.println("添加");
                    list.add(new byte[1024 * 1024 * 10]);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    Reference<? extends M> reference = queue.poll();
                    if(reference != null){
                        System.out.println(" ---- 虚引用被回收 ---- ");
                    }
                }
            }
        }).start();
    }
}


class Cleaner<T> extends PhantomReference<T>{

    private static final ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
    private final Runnable task;


    public Cleaner(T referent, Runnable task) {
        super(referent, referenceQueue);
        this.task = task;
    }

    public void clean(){
        try{
            new Thread(task).start();
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }
    }
}

class Deallocator implements Runnable{

    private static Unsafe unsafe = Unsafe.getUnsafe();

    private long address;
    private long size;
    private int capacity;

    public Deallocator(long address, long size, int capacity) {
        if(address != 0){
            this.address = address;
        }
        this.size = size;
        this.capacity = capacity;
    }

    @Override
    public void run() {
        if(address != 0){
            unsafe.freeMemory(address);
        }
    }
}



class M{
    @Override
    protected void finalize() throws Throwable {
        System.out.println("调用了finalize方法..");
    }
}
