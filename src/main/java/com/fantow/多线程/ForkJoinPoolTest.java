package com.fantow.多线程;

import java.util.concurrent.*;

public class ForkJoinPoolTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool service = new ForkJoinPool(2);

        ExecutorService service1 = Executors.newWorkStealingPool(2);


        Task task = new Task(1,100);
        ForkJoinTask<Integer> joinTask = service.submit(task);

            System.out.println(joinTask.get());

    }
}

class Task extends RecursiveTask<Integer>{

    private int start;
    private int end;

    public static int THRESHOLD = 2;

    public Task(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {

        boolean canCompute = false;

        if(end - start <= THRESHOLD){
            canCompute = true;
        }

        int sum = 0;

        if(canCompute){
            for(int i = start;i <= end;i++){
                sum += i;
            }

            return sum;
        }else{
            int mid = start + (end - start) / 2;

            Task task1 = new Task(start,mid);
            Task task2 = new Task(mid + 1,end);

            task1.fork();
            task2.fork();

            Integer leftJoin = task1.join();
            Integer rightJoin = task2.join();

            sum = leftJoin + rightJoin;

            return sum;
        }
    }
}
