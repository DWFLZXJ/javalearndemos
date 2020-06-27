package com.daiwf.javalearndemos.threadpooltest;

public class Task implements Runnable
{
    @Override public void run() {
        SingletonDemo lazysingleton = SingletonDemo.getInstance();
        System.out.println("线程： "+Thread.currentThread().getName()+"单例对象： "+lazysingleton);
    }
}
