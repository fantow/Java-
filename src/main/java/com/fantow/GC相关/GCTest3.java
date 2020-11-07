package com.fantow.GC相关;

public class GCTest3{
    // static类型和final类型分别在什么地方被分配内存？
    static Object object = new byte[1024 * 1024 * 10];


    public static void main(String[] args) {
        // 在堆中也创建一个10MB大小的对象，同时设置堆大小为15，看是否OOM
        byte[] bytes = new byte[1024 * 1024 *10];

        System.out.println("here...");
    }
}
