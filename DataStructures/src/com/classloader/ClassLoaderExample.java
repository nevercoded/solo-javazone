package com.classloader;

import com.ds.sync.BlockingQueueImpl;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: skumar
 * Date: 10/31/13
 * Time: 7:28 PM
 * Example usage of class loader
 */
public class ClassLoaderExample {

    private Class loadClass(String className){
        ClassLoader loader = this.getClass().getClassLoader();
        try {
            return loader.loadClass(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args){
        ClassLoaderExample example = new ClassLoaderExample();
        Class c = example.loadClass("com.ds.sync.BlockingQueueImpl");
        System.out.println(c.getName());
        Method[] methods =  c.getMethods();
        for(int i=0;i<methods.length;i++){
            System.out.println("Method=" + methods[i].getName());
        }

    }
}
