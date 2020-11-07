package com.fantow.多线程;

public class ClinitClassTest {
    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                A a = new A();
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                B b = new B();
            }
        }).start();

    }
}

class A{
    static{
        try {
            System.out.println("执行A的static");
            Thread.sleep(1000);
            B b = new B();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class B{
    static{
        System.out.println("执行B的static");
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        A a = new A();

    }

}
