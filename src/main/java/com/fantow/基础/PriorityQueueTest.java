package com.fantow.基础;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

public class PriorityQueueTest {
    public static void main(String[] args) {
        // 默认为小根堆
//        PriorityQueue<Integer> queue = new PriorityQueue<>();

        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });


        Random random = new Random();
        for(int i = 0;i < 10;i++){
            int integer = random.nextInt();
            queue.offer(integer);
        }

        while(!queue.isEmpty()){
            System.out.println(queue.poll());
        }

    }
}


