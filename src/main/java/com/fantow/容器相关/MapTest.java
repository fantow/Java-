package com.fantow.容器相关;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapTest {
    public static void main(String[] args) {
//        HashMap<String,mapTest1> map1 = new HashMap<>();
//        map1.put("1",new mapTest1("a"));
//        map1.put("2",new mapTest1("b"));
//        map1.put("3",new mapTest1("c"));
//
////        Map<String,mapTest1> map2 = new HashMap<>(map1);
//
//        HashMap<String,mapTest1> map2 = new HashMap<>();
//        map2.putAll(map1);
//
//        mapTest1 mapTest = map1.get("1");
//        mapTest.name = "aaa";
//
//        for(Map.Entry<String,mapTest1> entry : map2.entrySet()){
//            System.out.println("key:" + entry.getKey() + " : " + entry.getValue().name);
//        }

        ConcurrentHashMap<String,String> map = new ConcurrentHashMap<>();
        map.put("abc",null);


    }
}
class mapTest1{
    String name ;
    public mapTest1(String name) {
        this.name = name;
    }
}
