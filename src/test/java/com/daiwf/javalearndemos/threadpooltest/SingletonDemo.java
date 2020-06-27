package com.daiwf.javalearndemos.threadpooltest;

public class SingletonDemo
{
    private static SingletonDemo lazysingleton = null;

    public SingletonDemo() {
    }

    public static SingletonDemo getInstance() {
        if (lazysingleton == null) {
            lazysingleton = new SingletonDemo();
        }
        return lazysingleton;
    }
}
