package com.fantow.基础;

import static java.lang.Integer.*;
import static com.fantow.基础.TestClass.*;

public class StaticImport {
    public static void main(String[] args) {
//        System.out.println(Integer.MAX_VALUE);
        System.out.println(MAX_VALUE);

//        TestClass.Test1();

        // 静态导包所带来的好处
        Test1();

    }
}

class TestClass {

    public static void Test1(){
        System.out.println("test1");
    }
}