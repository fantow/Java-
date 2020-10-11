package com.fantow.多线程;

public class SynchronizedTest2 {
    // 当锁定的对象修改了引用时，
    public static void main(String[] args) {

        T t = new T();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程1执行");
                t.method();
            }
        });


        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程2执行");
                t.method();
            }
        });

        t1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 如果没有修改对象的锁，线程2永远不会获锁
        t.obj = new Object();
        t2.start();
    }
}

class T{
    public Object obj = new Object();

    public void method(){
        synchronized (obj){
            // 持续阻塞
            while(true){
                try {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + " 正在执行");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
