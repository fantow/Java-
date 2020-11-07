package com.fantow.基础;

public class ClassLoaderTest {
    public static void main(String[] args) {

        ClassLoader loader = String.class.getClassLoader();
        System.out.println(loader);

    }
}
