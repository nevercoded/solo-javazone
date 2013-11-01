package com.ds.sync;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Created with IntelliJ IDEA.
 * User: skumar
 * Date: 10/25/13
 * Time: 1:23 PM
 * Example of fork and join using RecursiveTask
 */
public class ForkJoinTaskExample {

    private static class Fibonacci extends RecursiveTask<Integer>{

        final int n;
        Fibonacci(int n){
            this.n = n;
        }
        @Override
        protected Integer compute() {
            if(n<=1)
                return n;
            Fibonacci f1 = new Fibonacci(n-1);
            f1.fork();
            Fibonacci f2 = new Fibonacci(n-2);
            return f2.compute()+f1.join();
        }
    }

    public static void main(String args[]){
        ForkJoinPool pool = new ForkJoinPool(2);
        System.out.println(pool.invoke(new Fibonacci(7)));
    }

}
