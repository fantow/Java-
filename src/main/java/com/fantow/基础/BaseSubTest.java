package com.fantow.基础;

public class BaseSubTest {
    // 测试父类,子类的执行顺序
}

class Parent{
    private String name = "Parent";

    public Parent() {
        call();
    }

    public void call(){
        System.out.println(name);
    }
}

class Sub extends Parent{
    private String name = "Sub";

    public void call(){
        System.out.println(name);
    }

    public static void main(String[] args) {
        Sub sub = new Sub();
    }
}
