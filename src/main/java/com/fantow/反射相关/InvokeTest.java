package com.fantow.反射相关;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

// 如果使用动态代理调用一个对象的方法，会不会改变这个对象的属性值？
public class InvokeTest {

    public static void main(String[] args) {
        Obj obj = new Obj();
//        obj.add();
//        int i = obj.get();
//        System.out.println(i);

//        Proxy.newProxyInstance(Thread.currentThread().getClass().getClassLoader(),Obj.class.getInterfaces(),)
        Class<Obj> aClass = Obj.class;
        try {
            System.out.println("before:" + obj.get());
            Method method = aClass.getMethod("add", null);
            Object result = method.invoke(obj, null);

            System.out.println("after:" + obj.get());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }
}

class MyInvocationHandler implements InvocationHandler{

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}


class Obj implements objInterface {
    private int a = 0;

    public void add() {
        System.out.println("add");
        a = 10;
    }

    public int get(){
        return a;
    }
}

interface objInterface{

}
