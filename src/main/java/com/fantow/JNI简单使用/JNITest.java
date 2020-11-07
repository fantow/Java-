package com.fantow.JNI简单使用;

public class JNITest {

    static{
        try{
            System.loadLibrary("sayHello");
        }catch (Exception ex){
            System.out.println("JNI方法调用失败...");
        }
        System.out.println("JNI方法调用成功...");
    }

    public native static String sayHello(String name);

    public static void main(String[] args) {
        System.out.println("Main方法调用了:" + sayHello("abc"));
    }

}
