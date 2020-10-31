package com.fantow.基础;

public class ParentAndSon {



    public static void main(String[] args) {
        Son son = new Son();
        Object obj = new Object();

    }
}
class Parent1{
    public Parent1() {
        test1();
    }

    public void test1(){
        System.out.println("父类的构造方法");
    }
}

class Son extends Parent1{

    public Son() {
    }

    @Override
    public void test1() {
        System.out.println("子类的构造方法");
    }
}

