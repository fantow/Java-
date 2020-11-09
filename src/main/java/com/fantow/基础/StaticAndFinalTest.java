package com.fantow.基础;

public class StaticAndFinalTest {

//    不会主动调用B的构造器
    public final B b = new B();

    // 会主动调用B的构造器
//    public static B b = new B();

    // 会主动调用B的构造器
//    public static final B b = new B();


    static{
        System.out.println("主类初始化...");
    }

    public static void main(String[] args) {
        new StaticAndFinalTest();
    }

    public void test(){
        int a = 10;
    }

//    private static Object object;
//    private final Object object2 = new Object();
//    private static final Object object3;
//
//    static{
//        object3 = null;
//    }

//
//    public static void main(String[] args) {
//        new A();
//    }
}


//class A{
//    public final B b = new B();
//}
//
class B{
    static {
        System.out.println("B类被加载...");
    }
}