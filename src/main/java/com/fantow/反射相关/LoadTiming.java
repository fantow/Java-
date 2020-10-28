package com.fantow.反射相关;

// 检测数组类的类加载时机
public class LoadTiming {
    public static void main(String[] args) {
        // 不会引起类的主动加载
        Object1[] objs = new Object1[10];

        // 会引起类的主动加载
        Object1 o1 = new Object1();
    }
}

class Object1{
    public Object1() {
        System.out.println("类被加载...");
    }
}
