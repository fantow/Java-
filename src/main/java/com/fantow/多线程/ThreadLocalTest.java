package com.fantow.多线程;

public class ThreadLocalTest {
    static ThreadLocal<Person> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {

        Person person = new Person();

        new Thread(new Runnable() {
            @Override
            public void run() {
                person.name = "Thread1";
                threadLocal.set(person);
                Person person1 = threadLocal.get();
                System.out.println(person1.name);
            }
        }).start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                person.name = "Thread2";
                threadLocal.set(person);
                Person person2 = threadLocal.get();
                System.out.println(person2.name);
            }
        }).start();


    }
}

class Person{
    String name = "name1";
}