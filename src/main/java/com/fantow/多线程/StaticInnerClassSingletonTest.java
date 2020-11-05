package com.fantow.多线程;

public class StaticInnerClassSingletonTest {

}

class Singleton2{
    private static Singleton2 instance = new Singleton2();

    private Singleton2(){

    }

    private static class SingletonHolder{
        public static Singleton2 INSTANCE = new Singleton2();
    }

    public static Singleton2 getInstance(){
        return SingletonHolder.INSTANCE;
    }

}
