package com.ds.sync.test;

import com.ds.sync.SynchronizedList;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 * User: skumar
 * Date: 10/13/13
 * Time: 2:51 PM
 * Test SynchronizedList
 */
public class Tests {

    private SynchronizedList _list = null;

    private class TestThread extends Thread{
        private String value;

        public TestThread(String value){
            this.value = value;
        }

        public void run(){
            System.out.println("Thread:"+value);
            for(int i=0;i<10;i++){
                _list.add(new Integer(i));
            }
            while(true){
                if(Thread.interrupted()){
                    System.out.println("Thread:"+value+" interrupted");
                    return;
                }
            }
        }
    }

    private class TestThreadRunner implements Runnable{
        private String _value = null;
        public TestThreadRunner(String value){_value = value;}

        @Override
        public void run(){
            System.out.println("Thread:"+_value);
            for(int i=0;i<10;i++){
                _list.add(new Integer(i));
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    System.out.println("Thread:"+_value+" interrupted");
                }
            }
        }
    }

    private class TestNormalThread implements Runnable{
        private String value = null;
        public TestNormalThread(String value){this.value=value;}
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                System.out.println("Thread:"+value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Before
    public void setUp() throws Exception {
        _list = new SynchronizedList();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGet() throws Exception {
        SynchronizedList list = new SynchronizedList();
        for(int i=0;i<10000;i++){
            list.add(new Integer(i));
        }
        for(int i=0;i<10000;i++){
            Integer intObject = (Integer) list.get(i);
            Assert.assertTrue(i==intObject.intValue());
        }
        list.set(new Integer(2*2000),2000);
        Assert.assertTrue(4000==((Integer)list.get(2000)).intValue());
    }

    @Test
    public void testThread() throws Exception {
        TestThread thread1 = new TestThread("thread1");
        thread1.start();
        thread1.interrupt();
        TestThreadRunner thread2 = new TestThreadRunner("thread2");
        Thread newThread2 = new Thread(thread2);
        newThread2.start();
        //newThread2.join();
        newThread2.interrupt();
    }

    @Test
    public void testThreadJoin() throws Exception{
        TestNormalThread thread1 = new TestNormalThread("ThreadJoin1");
        Thread t1 = new Thread(thread1);
        t1.start();
        if(t1.isAlive()){
            t1.join();
            System.out.println("Current Thread is running now");
            Assert.assertTrue(Thread.currentThread().isAlive());
        }
    }

    @Test
    public void testLockObject(){
        Lock lock2 = new ReentrantLock();
        lock2.lock();
        try{
            // DO something
            System.out.println("Now this is critical section");

        }finally {
            lock2.unlock();
        }
    }

    @Test
    public void testExecutorServiceSingleThreadExecutor() throws InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Executor Service SingleThreadExecutor1");
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("Executor Service SingleThreadExecutor2");
            }
        });
        // Wait for some time for the threads to finish
        // Here the threads should finish in sequence i.e. 1 and then 2.
        Thread.sleep(3000);
        executorService.shutdown();
    }

    @Test
    public void testExecutorServiceFixedThreadPool() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Executor Service SingleThreadExecutor1");
            }
        });
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("Executor Service SingleThreadExecutor2");
            }
        });
        // In this test thread 2 should finish and then 1 as they will be running in parallel.
        Thread.sleep(3000);
        executorService.shutdown();
    }

    @Test
    public void testExecutorServiceSubmitRunnable() throws Exception {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future future = executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Executor Service SingleThreadExecutor");
            }
        });
        Assert.assertTrue(future.get()==null);
    }

}
