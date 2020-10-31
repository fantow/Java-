package com.fantow.反射相关;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKDynamicProxy2 implements InvocationHandler {

    Object target;

    public JDKDynamicProxy2(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("方法的前置方法...");
        Object o = method.invoke(target, args);
        System.out.println("方法的后置方法...");
        return o;
    }

    public Object getProxy(){
        Object instance = Proxy.newProxyInstance(this.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
        return instance;
    }

    public static void main(String[] args) {

        AClass target = new AClass();
        JDKDynamicProxy2 proxy = new JDKDynamicProxy2(target);
        AInterface proxy1 = (AInterface) proxy.getProxy();
        proxy1.test1();

        try {
            Class<?> aClass = Class.forName("java.lang.Integer");
            aClass.newInstance();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }


    }

}

interface AInterface{
    void test1();
}

class AClass implements AInterface{
    @Override
    public void test1() {
        System.out.println("执行了方法...");
    }
}
