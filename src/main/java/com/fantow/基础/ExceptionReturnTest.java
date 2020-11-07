package com.fantow.基础;

public class ExceptionReturnTest {
    public static void main(String[] args) {

    }


    public static int test1(){
        int x;
        try {
            x = 1;
            return x;
        } catch (Exception e) {
            x = 2;
            return x;
        } finally {
            x = 3;
        }
    }

    public static int test2(){
        test3();
        return 1;
    }

    public static void test3(){

    }

}
