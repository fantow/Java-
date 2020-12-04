package com.fantow.算法相关;

import java.util.HashMap;
import java.util.Map;

public class TimeTest {
    public static void main(String[] args) {

        for(int i = 1;i < 10;i++){
            // 本次测试上界
            double max = Math.pow(10.0,i);

            long startTime = System.currentTimeMillis();

            int sum = 0;
            for(int j = 0;j < max;j++){
                sum += j;
            }
            long endTime = System.currentTimeMillis();

            System.out.println("10^" + i + " 耗时：" + (endTime - startTime));
        }

    }
}

