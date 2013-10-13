package com.ds.sync.test;

import com.ds.sync.SynchronizedList;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: skumar
 * Date: 10/13/13
 * Time: 2:51 PM
 * Test SynchronizedList
 */
public class SynchronizedListTest{
    @Before
    public void setUp() throws Exception {

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
        System.out.println(((Integer)list.get(2000)).intValue());
        Assert.assertTrue(4000==((Integer)list.get(2000)).intValue());
    }

    @Test
    public void testSet() throws Exception {

    }

}
