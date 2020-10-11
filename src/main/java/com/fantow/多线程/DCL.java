package com.fantow.多线程;

// 一个线程安全的双重检查锁单例
public class DCL {

    private static volatile DCL instance = null;

    private DCL(){

    }

    public static DCL getInstance(){
        if(instance == null){
            synchronized (instance){
                if(instance == null){
                    instance = new DCL();
                }
            }
        }
        return instance;
    }
}


// 另一种线程安全的单例模式
class DCL2{

    private DCL2(){

    }

    private static class SingleHolder{
        // 对于被static final修饰的变量，会在类的准备阶段附上自身的值
        // 被static修饰的变量，会在类的初始化阶段赋上自身的值
        // 普通变量，会在new对象时，使用<init>方法赋值
        private static final DCL2 instance = new DCL2();
    }

    // 静态内部类不依赖外部类而存在，只有使用到时才进行加载。属于懒加载
    public static DCL2 getInstance(){
        return SingleHolder.instance;
    }
}


// 饿汉式单例模式,饿汉式一定是线程安全的
class SingleInstance{

    private static SingleInstance instance = new SingleInstance();

    private SingleInstance(){

    }

    public static SingleInstance getInstance(){
        return instance;
    }
}
