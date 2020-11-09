package com.fantow.基础;

public class InternStringTest {
    public static void main(String[] args) {
        String ab = new String("a") + new String("b");

        String str = ab.intern();
        System.out.println("ab" == ab);
        System.out.println("ab" == str);
    }
}
