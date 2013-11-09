package com.nestedclasses;

/**
 * Created with IntelliJ IDEA.
 * User: skumar
 * Date: 11/8/13
 * Time: 4:15 PM
 * Nested classes examples
 */

public class NestedClassExample {

    private String instanceVariable = "instance";
    private static String staticVariable = "static";
    public interface InnerAnonymous{
        public void display();
    }

    /**
     * Inner member class
     */
    private class InnerClassOne{
        private String name = null;
        public InnerClassOne(String name){
            this.name = name;
        }
        public void display(){
            System.out.println(name);
            System.out.println(instanceVariable);
            System.out.println(staticVariable);
        }
    }

    /**
     * Inner static class
     */
    private static class InnerClassTwo{
        private String name = null;
        public InnerClassTwo(String name){
            this.name = name;
        }
        public void display(){
            System.out.println(name);
            System.out.println(staticVariable);
        }
    }

    public void displayInnerClass(String name){
        InnerClassOne classOne = new InnerClassOne(name);
        classOne.display();
        // creating anonymous class with extending display()
        InnerClassOne o1 = new InnerClassOne(name){
            public void display(){
                System.out.println("1");
            }
        };
    }

    public void displayInnerAnonymous(InnerAnonymous innerAnonymous){
        innerAnonymous.display();
    }

    public static void main(String args[]){
        NestedClassExample example = new NestedClassExample();
        example.displayInnerClass("One");
        NestedClassExample.InnerClassTwo two = new InnerClassTwo("Two");
        two.display();
        example.displayInnerAnonymous(new InnerAnonymous() {
            @Override
            public void display() {
                System.out.println("Anonymous");
            }
        });
        NestedClassExample.InnerClassOne o1 = example.new InnerClassOne("One1");
        o1.display();
    }

}

/* This is package class
 */
class PackageClassTwo{
    private String name = null;
    public PackageClassTwo(String name){
        this.name = name;
    }
    public void display(){
        System.out.println(name);
    }
}
