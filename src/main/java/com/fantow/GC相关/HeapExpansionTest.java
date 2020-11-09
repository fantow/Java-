package com.fantow.GC相关;

public class HeapExpansionTest {
    // 测试堆得扩容
    public static void main(String[] args) {
        byte[] bytes = new byte[1024 * 1024 * 10];
        System.out.println("创建一个10MB的对象");
//        try {
//            Thread.sleep(4000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("创建另一个10MB的对象");
        byte[] bytes2 = new byte[1024 * 1024 * 10];


        System.out.println("结束...");
    }
}
