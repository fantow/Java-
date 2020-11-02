package com.fantow.多线程;

public class YieldMySelfTest {
    public static void main(String[] args) {
        Thread thread = Thread.currentThread();
        thread.yield();
    }
}
