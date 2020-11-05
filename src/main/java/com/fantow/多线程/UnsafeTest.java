package com.fantow.多线程;

import sun.misc.Unsafe;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class UnsafeTest {
    public static void main(String[] args) {

        try {
            Field unsafeFiled = Unsafe.class.getDeclaredField("theUnsafe");
            unsafeFiled.setAccessible(true);

            Unsafe unsafe = (Unsafe) unsafeFiled.get(null);

//            long memory = unsafe.allocateMemory(1024);
//            System.out.println(memory);


            // 如果使用CAS的compareAndSwapObject，swap其他的属性，会怎么样？？
//            TestClazz clazz = new TestClazz();

            // 获取clazz的field的offset
//            long offset = unsafe.objectFieldOffset
//                    (TestClazz.class.getDeclaredField("field1"));

//            clazz.field1 = 10;
//            String str = "20";
//            unsafe.compareAndSwapObject(clazz, offset,clazz.field1,str);
//
//            System.out.println(clazz.field1.getClass());


            Class<TestClazz2> aClass = TestClazz2.class;

            // 因为没有空参构造器，所以会报错
//            TestClazz2 instance = aClass.newInstance();
//            System.out.println(instance.name);


//            Constructor<?> constructor = aClass.getDeclaredConstructors()[0];
//            TestClazz2 instance = (TestClazz2) constructor.newInstance();
//            System.out.println(instance.name);

            long offset = unsafe.objectFieldOffset(TestClazz2.class.getDeclaredField("name"));

            TestClazz2 instance = (TestClazz2)unsafe.allocateInstance(aClass);

            String name = "123";
            unsafe.putObjectVolatile(instance,offset,name);

            System.out.println(instance.name);

//            System.out.println(unsafe.getClass());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }
}

class TestClazz{
    Integer field1;
}

class TestClazz2{

    String name;
    int age;

    public TestClazz2(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
