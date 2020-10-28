package com.fantow.基础;

public class StaticTest {

    public static Object object = new Object();

    public static void main(String[] args) {
        StaticTest test1 = new StaticTest();
        StaticTest test2 = new StaticTest();


        System.out.println(test1.object == test2.object);
    }
}
