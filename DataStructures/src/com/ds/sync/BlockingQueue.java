package com.ds.sync;

import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: Sushanth
 * Date: 10/17/13
 * Time: 9:36 PM
 * Blocking Queue implementation
 */
public class BlockingQueue {

    LinkedList queue = new LinkedList();
    int limit = 10;

    public BlockingQueue(int limit){
        this.limit = limit;
    }

    public synchronized void enqueue(Object data) throws InterruptedException {
        while(queue.size() == limit){
            wait();
        }
        if(queue.size() == 0){
           notifyAll();
        }
        queue.add(data);
    }

    public synchronized Object dequeue() throws InterruptedException {
        while(queue.size()==0){
            wait();
        }
        if(queue.size() == limit){
            notifyAll();
        }
        return queue.remove();
    }


}
