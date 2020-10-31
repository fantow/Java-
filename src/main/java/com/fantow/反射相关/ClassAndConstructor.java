package com.fantow.反射相关;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ClassAndConstructor {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> aClass = Class.forName("com.fantow.反射相关.MTest");

        Constructor<?> constructor = aClass.getDeclaredConstructor();

        constructor.setAccessible(false);

        try{
            MTest instance1 = (MTest) constructor.newInstance();

        }catch (Exception ex){
            ex.printStackTrace();
        }
        System.out.println("-----");
        MTest instance2 = (MTest) aClass.newInstance();

    }
}

class MTest{

    String name = "123";

    private MTest(){
        System.out.println("无参构造器");
    }

    public MTest(String name) {
        this.name = name;
        System.out.println("有参构造器");
    }

}
