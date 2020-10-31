package com.fantow.基础;

public class CastTest {
    public static void main(String[] args) {

        int a = 10;

        // 提供了自动向上转换
        double b = a;

        // 无法隐式进行类型转换
        short c = (short)a;

        // 对于++,+= 这样的句式，可以进行隐式的类型转换
        c++;

        c += 1;

    }
}
