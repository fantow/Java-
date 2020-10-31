package com.fantow.基础;

import java.lang.reflect.Method;

public class StaticSingleton {

    private StaticSingleton(){
    }

    static class SingletonHolder{
        private static StaticSingleton INSTANCE = new StaticSingleton();
    }

    public static StaticSingleton getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public static void main(String[] args) {



    }


}
