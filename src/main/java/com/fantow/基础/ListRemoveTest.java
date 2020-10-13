package com.fantow.基础;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ListRemoveTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        for(int i = 0;i < 10;i++){
            list.add(i);
        }

        // 尝试边遍历边删除 --> ConcurrentModificationException
        Iterator<Integer> iterator = list.iterator();
//        while(iterator.hasNext()){
//            Integer integer = iterator.next();
//            System.out.println(integer);
//            if(integer == 4){
//                list.remove(4);
//            }
//        }
//

        // 使用iterator.remove()删除
//        while(iterator.hasNext()){
//            Integer integer = iterator.next();
//            System.out.println(integer);
//            if(integer == 4){
//                iterator.remove();
//            }
//        }

        // 尝试倒序删除
//        for(int i = 9;i >= 0;i--){
//            System.out.println(i);
//            if(list.get(i) == 4){
//                list.remove(i);
//            }
//        }

    }
}
