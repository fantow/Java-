package com.fantow.基础;

import java.util.*;

public class CollectionTest {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();

        List<MyClz> list = new ArrayList<>();
        list.add(new MyClz("A"));
        list.add(new MyClz("C"));
        list.add(new MyClz("B"));

        Set<Integer> set = new HashSet<>();


        Collections.sort(list, new Comparator<MyClz>() {
            @Override
            public int compare(MyClz o1, MyClz o2) {
                return o1.str.compareTo(o2.str);
            }
        });

        for(MyClz i : list){
            System.out.println(i.str);
        }

    }
}
//class MyClz implements Comparable{
//    public String str;
//
//    public MyClz(String str) {
//        this.str = str;
//    }
//
//    @Override
//    public int compareTo(Object o) {
//        return str.compareTo(((MyClz)o).str);
//    }
//}

class MyClz{
    public String str;

    public MyClz(String str) {
        this.str = str;
    }
}
