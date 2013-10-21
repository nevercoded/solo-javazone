package com.ds.sync;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created with IntelliJ IDEA.
 * User: skumar
 * Date: 10/20/13
 * Time: 3:27 PM
 * Example implementation of producer consumer problem
 */
public class ProducerConsumer {
    private BlockingQueue queue = new LinkedBlockingQueue(10);

    public static void main(String arg[]){
        ProducerConsumer producerConsumer = new ProducerConsumer();
        Thread producer = new Thread(new Producer(producerConsumer.queue));
        Thread consumer = new Thread(new Consumer(producerConsumer.queue));
        producer.start();
        consumer.start();
    }


}

class Producer implements Runnable{

    private final BlockingQueue queue;

    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while(true){
            for(int i=0;i<10;i++){
                try {
                    queue.put(i);
                } catch (InterruptedException e) {
                }
            }
        }
    }
}

class Consumer implements Runnable{

    private final BlockingQueue queue;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true){
            try {
                Integer i = (Integer) queue.take();
                System.out.println("Taken value:"+i);
            } catch (InterruptedException e) {

            }
        }
    }
}