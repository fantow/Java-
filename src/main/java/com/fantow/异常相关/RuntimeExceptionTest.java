package com.fantow.异常相关;

public class RuntimeExceptionTest {
    public static void main(String[] args) {

        try{
            test();
        }catch (InterruptedException ex){
            ex.printStackTrace();
        }


    }

    public static void test() throws InterruptedException{
        return ;
    }

}
