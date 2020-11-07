package com.fantow.基础;

public class StaticAllocTest {
    public static void main(String[] args) {
        // 重载的匹配优先级
        char ch = 'a';

        AClass aClass = new AClass();
        aClass.test1(ch);
    }
}

class AClass{

    public char test1(char ch){
        System.out.println("char...");
        return ch;
    }

    public int test1(int ch){
        System.out.println("int...");
        return ch;
    }

}