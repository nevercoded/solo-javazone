package com.ds.sync;

import com.ds.sync.exception.InvalidIndexException;import java.io.Serializable;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: skumar
 * Date: 10/13/13
 * Time: 2:05 PM
 * Synchronized implementation of linked list.
 */

public class SynchronizedList implements Serializable{

    private static final int INITIAL_CAPACITY = 10;
    private transient Object[] elementData;
    private int size;

    public SynchronizedList(){
        this.elementData = new Object[INITIAL_CAPACITY];
    }

    public SynchronizedList(int capacity){
        if(capacity<=0){
            throw new IllegalArgumentException("Capacity value should be positive");
        }
        size=0;
        this.elementData = new Object[capacity];
    }

    public Object get(int index){
        // Check for valid index. It should be < the total capacity
        if(index<0 && index>elementData.length){
            throw new InvalidIndexException("Index out of bound");
        }
        return elementData[index];
    }

    public synchronized void set(Object data, int index){
        if(index<0){
            throw new InvalidIndexException("Index out of bound");
        }
        checkCapacity(index);
        elementData[index] = data;
    }

    public synchronized void add(Object data){
        checkCapacity(size+1);
        elementData[size++] = data;
    }

    /**
     * Increase the capacity if needed
     * @param index
     */
    private void checkCapacity(int index) {
        //System.out.println("index="+index);
        if(index>=elementData.length){
            int currentCapacity = elementData.length;
            int newCapacity = currentCapacity + (currentCapacity >> 1);
            //System.out.println("Capacity="+newCapacity);
            elementData = Arrays.copyOf(elementData,newCapacity);
        }
    }


}
