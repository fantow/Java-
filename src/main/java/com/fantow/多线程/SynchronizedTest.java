package com.fantow.多线程;

public class SynchronizedTest {
    public static void main(String[] args) {

        Parent parent = new Parent();
        Son son = new Son();

        parent.method1();
        parent.method2();

        son.method1();
        son.method2();
    }
}

class Parent{

    public synchronized void method1(){
        System.out.println("父类被synchronized修饰的方法");
        System.out.println("this方法:" + this);
    }

    public void method2(){
        synchronized (this) {
            System.out.println("父类普通方法");
            System.out.println("this方法:" + this);
        }
    }
}


class Son extends Parent{

    @Override
    public synchronized void method1() {
        System.out.println("子类被synchronized修饰的方法");
        System.out.println("this方法:" + this);
    }

//    @Override
//    public void method2() {
//        synchronized (this) {
//            System.out.println("子类普通方法");
//            System.out.println("this方法:" + this);
//        }
//    }
}
