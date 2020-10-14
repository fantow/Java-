package com.fantow.多线程;

import java.util.concurrent.*;

public class ForkJoinTest {
    public static void main(String[] args) {
        ForkJoinPool service = new ForkJoinPool();
        MyTast tast = new MyTast(1,100);
        service.submit(tast);

        try {
            System.out.println(tast.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}


class MyTast extends RecursiveTask<Integer>{

    public static int THREDSHOLD = 2;

    public int start;
    public int end;

    public MyTast(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int result = 0;

        boolean canCompute = (end - start) <= THREDSHOLD;

        if(canCompute){
            for(int i = start;i <= end;i++){
                result += i;
            }
        }else{
            int middle = start + (end - start) / 2;

            MyTast task1 = new MyTast(start,middle);
            MyTast task2 = new MyTast(middle + 1,end);

            task1.fork();
            task2.fork();

            int leftResult = task1.join();
            int rightResult = task2.join();

            result = leftResult + rightResult;
        }

        return result;
    }
}
