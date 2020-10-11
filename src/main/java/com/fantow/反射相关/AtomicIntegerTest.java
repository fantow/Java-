package com.fantow.反射相关;

import com.sun.org.apache.xpath.internal.objects.XObject;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {
    // 一直有个问题，在AtomicInteger中的valueOffset是用static final修饰的属性
    // 它会在类的加载阶段赋值，这时还没有new出来内存空间，那么它的valueOffset存的到底是什么？
//    public static void main(String[] args) throws IllegalAccessException {
//        // valueOffset 恒为12(应该是指偏移量吧，内存地址是不确定，但是valueOffset所代表的的是这个值在内存中的偏移量)
//        AtomicInteger atomicInteger = new AtomicInteger(1);
//
//        Field[] fields = atomicInteger.getClass().getDeclaredFields();
//        for(Field field : fields){
//            field.setAccessible(true);
//            System.out.println(field.getType().getName() + " : " + field.get(atomicInteger));
//        }
//
//    }

    // 再来测试一下，引用指针占多少字节
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe)field.get(null);
        long intAddress = unsafe.objectFieldOffset(Test.class.getDeclaredField("a"));
        long intAddress2 = unsafe.objectFieldOffset(Test.class.getDeclaredField("b"));
        long StringAddress = unsafe.objectFieldOffset(Test.class.getDeclaredField("str"));
        long ObjectAddress = unsafe.objectFieldOffset(Test.class.getDeclaredField("obj"));

        System.out.println(intAddress); // 12
        System.out.println(intAddress2); // 16
        System.out.println(StringAddress); // 20
        System.out.println(ObjectAddress); // 24
    }

}

class Test{

    int a = 10;
    int b = 20;
    String str = "123";

    Object obj = new Object();

}
