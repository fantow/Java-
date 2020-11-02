package com.fantow.GC相关;

// 如果类中有其他对象的引用，都失去了强引用，谁会先调用finialized?
public class GCTest2 {
    public static void main(String[] args) throws InterruptedException {
//        A a = new A();
//
//        a = null;
//
//        // 测试结果是先调用了B的finalize()，再调用A的finalize()
//        Thread.sleep(1000);
//
//        System.gc();

        // 再来测试一下，被synchronized锁住的对象是否会被回收？
        Object obj = new Object();

        synchronized (obj){
            obj.wait();
        }

        

    }
}

class A {

    public B field = new B();

    @Override
    protected void finalize() throws Throwable {
        System.out.println("调用了A的finalize...");
    }
}

class B{

    public String str = "123";

    @Override
    protected void finalize() throws Throwable {
        System.out.println("调用了B的finalize...");
    }
}