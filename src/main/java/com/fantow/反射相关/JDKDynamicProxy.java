package com.fantow.反射相关;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKDynamicProxy implements InvocationHandler {

    public Object target;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("前置通知...");

        // 这个应该填target，而不是proxy
        Object invoke = method.invoke(target, args);
        System.out.println("后置通知...");
        return invoke;
    }

    public Object getProxy(Object target){
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }

    public static void main(String[] args) {
        MyTest1 test = new MyTest1();
        MyTest1Interface proxy = (MyTest1Interface) new JDKDynamicProxy().getProxy(test);
        proxy.test1();
    }
}

interface MyTest1Interface{
    public void test1();
}

class MyTest1 implements MyTest1Interface{

    @Override
    public void test1() {
        System.out.println("执行了方法");
    }
}